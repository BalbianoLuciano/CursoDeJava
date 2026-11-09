package Practica.calculadorabase3;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CalculadoraBase3 {
  private static final int MAXIMO_INTENTOS = 3;

  public static void main(String[] args) {
    int operacionesRealizadas = 0;

    // try-with-resources: el Scanner se cierra solo al salir del bloque, haya error o no
    try (Scanner scanner = new Scanner(System.in)) {
      boolean seguir = true;
      while (seguir) {
        mostrarMenu();
        try {
          int opcion = leerOpcion(scanner);
          if (opcion == 0) {
            seguir = false;
          } else {
            NumeroBase3 a = leerNumero(scanner, "Primer número: ");
            NumeroBase3 b = leerNumero(scanner, "Segundo número: ");
            mostrarResultado(opcion, a, b);
            operacionesRealizadas++;
          }
        } catch (OperacionInvalidaException e) {
          // error previsto y fácil de corregir: se avisa y se vuelve al menú
          System.out.println("Error: " + e.getMessage());
        } catch (CalculadoraBase3Exception e) {
          // red de seguridad para el resto de la familia; acá llega, por ejemplo, el
          // NumeroInvalidoException que relanza leerNumero al agotar los intentos
          System.out.println("Error en la calculadora: " + e.getMessage() + " Volvés al menú.");
        }
      }
      System.out.println("¡Hasta luego!");
    } catch (NoSuchElementException e) {
      // excepción NO controlada de Scanner: no hay más entrada (por ejemplo, Ctrl+D)
      System.out.println("\nSe terminó la entrada.");
    } catch (Exception e) {
      // último recurso: algo que no previmos; el programa no se cae sin explicación
      System.out.println("Error inesperado: " + e);
    } finally {
      // se ejecuta siempre, termine bien o con error
      System.out.println("Operaciones realizadas: " + operacionesRealizadas);
    }
  }

  private static void mostrarMenu() {
    System.out.println("\n=== Calculadora en base 3 ===");
    System.out.println("1. Sumar");
    System.out.println("2. Restar");
    System.out.println("3. Multiplicar");
    System.out.println("0. Salir");
    System.out.print("Elegí una opción: ");
  }

  private static int leerOpcion(Scanner scanner) throws OperacionInvalidaException {
    String linea = scanner.nextLine().trim();
    try {
      int opcion = Integer.parseInt(linea);
      if (opcion < 0 || opcion > 3) {
        throw new OperacionInvalidaException(linea);
      }
      return opcion;
    } catch (NumberFormatException e) {
      // se "traduce" la excepción de Java a una propia, con un mensaje que entiende el usuario
      throw new OperacionInvalidaException(linea);
    }
  }

  /**
   * Pide el número hasta que sea válido, con un máximo de intentos.
   * Los catch van del más específico al más general: si se invierte el orden, no compila.
   */
  private static NumeroBase3 leerNumero(Scanner scanner, String mensaje) throws NumeroInvalidoException {
    NumeroInvalidoException ultimoError = null;
    for (int intento = 1; intento <= MAXIMO_INTENTOS; intento++) {
      System.out.print(mensaje);
      String texto = scanner.nextLine();
      try {
        return new NumeroBase3(texto);
      } catch (NumeroVacioException e) {
        System.out.println("Error: " + e.getMessage());
        ultimoError = e;
      } catch (DigitoInvalidoException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("  " + e.getTexto());
        System.out.println("  " + " ".repeat(e.getPosicion()) + "^ acá");
        ultimoError = e;
      } catch (NumeroInvalidoException e) {
        // atrapa lo que no atraparon los catch de arriba (por ejemplo, "-" solo)
        System.out.println("Error: " + e.getMessage());
        ultimoError = e;
      }
    }
    // se agotaron los intentos: se relanza el último error para que lo maneje quien llamó
    throw ultimoError;
  }

  private static void mostrarResultado(int opcion, NumeroBase3 a, NumeroBase3 b) {
    NumeroBase3 resultado;
    String simbolo;
    switch (opcion) {
      case 1:
        resultado = a.sumar(b);
        simbolo = "+";
        break;
      case 2:
        resultado = a.restar(b);
        simbolo = "-";
        break;
      default:
        resultado = a.multiplicar(b);
        simbolo = "x";
    }
    System.out.println(a + " " + simbolo + " " + b + " = " + resultado + "  (base 3)");

    // try anidado: si la comprobación en decimal falla, el resultado en base 3 igual se muestra
    try {
      System.out.println(a.aDecimal() + " " + simbolo + " " + b.aDecimal() + " = " + resultado.aDecimal()
          + "  (en decimal, para comprobar)");
    } catch (DesbordamientoException e) {
      System.out.println("(No se puede comprobar en decimal: " + e.getMessage() + ")");
    }
  }
}
