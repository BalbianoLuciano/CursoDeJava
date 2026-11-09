package Practica.calculadorabase3;

/**
 * Número entero en base 3 (dígitos 0, 1 y 2), con signo opcional.
 * Las operaciones se hacen dígito por dígito en base 3, sin pasar por decimal.
 */
public class NumeroBase3 {
  private final boolean negativo;
  private final String digitos; // sin signo y sin ceros a la izquierda

  public NumeroBase3(String texto) throws NumeroInvalidoException {
    validar(texto);
    texto = texto.trim();
    boolean conSigno = texto.startsWith("-");
    String sinSigno = quitarCerosIzquierda(conSigno ? texto.substring(1) : texto);
    this.digitos = sinSigno;
    this.negativo = conSigno && !sinSigno.equals("0"); // -0 es 0
  }

  private NumeroBase3(boolean negativo, String digitos) {
    this.digitos = quitarCerosIzquierda(digitos);
    this.negativo = negativo && !this.digitos.equals("0");
  }

  /** Lanza la excepción más específica que corresponda al error encontrado. */
  private static void validar(String texto) throws NumeroInvalidoException {
    if (texto == null || texto.isBlank()) {
      throw new NumeroVacioException();
    }
    String limpio = texto.trim();
    if (limpio.equals("-")) {
      // no encaja en ninguna subclase: se lanza la excepción general del grupo
      throw new NumeroInvalidoException("Falta el número después del '-'.", limpio);
    }
    int inicio = limpio.startsWith("-") ? 1 : 0;
    for (int i = inicio; i < limpio.length(); i++) {
      char c = limpio.charAt(i);
      if (c != '0' && c != '1' && c != '2') {
        throw new DigitoInvalidoException(limpio, i);
      }
    }
  }

  public NumeroBase3 sumar(NumeroBase3 otro) {
    if (this.negativo == otro.negativo) {
      // mismo signo: se suman las magnitudes y se conserva el signo
      return new NumeroBase3(this.negativo, sumarMagnitudes(this.digitos, otro.digitos));
    }
    // distinto signo: al mayor se le resta el menor y queda el signo del mayor
    if (compararMagnitudes(this.digitos, otro.digitos) >= 0) {
      return new NumeroBase3(this.negativo, restarMagnitudes(this.digitos, otro.digitos));
    }
    return new NumeroBase3(otro.negativo, restarMagnitudes(otro.digitos, this.digitos));
  }

  public NumeroBase3 restar(NumeroBase3 otro) {
    // a - b = a + (-b)
    return sumar(new NumeroBase3(!otro.negativo, otro.digitos));
  }

  public NumeroBase3 multiplicar(NumeroBase3 otro) {
    String resultado = "0";
    String desplazado = this.digitos;
    // como en la multiplicación a mano: un renglón por cada dígito del otro número,
    // empezando por la derecha y corriendo un lugar (agregando un 0) en cada renglón
    for (int i = otro.digitos.length() - 1; i >= 0; i--) {
      int digito = otro.digitos.charAt(i) - '0';
      for (int vez = 0; vez < digito; vez++) {
        resultado = sumarMagnitudes(resultado, desplazado);
      }
      desplazado = desplazado + "0";
    }
    return new NumeroBase3(this.negativo != otro.negativo, resultado);
  }

  /** Solo para mostrar el equivalente y comprobar los resultados. */
  public long aDecimal() throws DesbordamientoException {
    if (digitos.length() > DesbordamientoException.MAXIMO_DIGITOS) {
      throw new DesbordamientoException(toString());
    }
    long valor = 0;
    for (int i = 0; i < digitos.length(); i++) {
      valor = valor * 3 + (digitos.charAt(i) - '0');
    }
    return negativo ? -valor : valor;
  }

  @Override
  public String toString() {
    return (negativo ? "-" : "") + digitos;
  }

  private static String sumarMagnitudes(String a, String b) {
    StringBuilder resultado = new StringBuilder();
    int i = a.length() - 1;
    int j = b.length() - 1;
    int acarreo = 0;
    while (i >= 0 || j >= 0 || acarreo > 0) {
      int suma = acarreo;
      if (i >= 0) {
        suma += a.charAt(i--) - '0';
      }
      if (j >= 0) {
        suma += b.charAt(j--) - '0';
      }
      resultado.append(suma % 3); // lo que queda en esta columna
      acarreo = suma / 3; // lo que "me llevo" a la siguiente
    }
    return resultado.reverse().toString();
  }

  /** Resta a - b suponiendo que a >= b. */
  private static String restarMagnitudes(String a, String b) {
    StringBuilder resultado = new StringBuilder();
    int i = a.length() - 1;
    int j = b.length() - 1;
    int prestamo = 0;
    while (i >= 0) {
      int resta = (a.charAt(i--) - '0') - prestamo;
      if (j >= 0) {
        resta -= b.charAt(j--) - '0';
      }
      if (resta < 0) {
        resta += 3; // se pide prestado 1 a la columna de la izquierda, que vale 3
        prestamo = 1;
      } else {
        prestamo = 0;
      }
      resultado.append(resta);
    }
    return quitarCerosIzquierda(resultado.reverse().toString());
  }

  private static int compararMagnitudes(String a, String b) {
    if (a.length() != b.length()) {
      return a.length() - b.length();
    }
    return a.compareTo(b); // mismo largo: alcanza con comparar el texto
  }

  private static String quitarCerosIzquierda(String texto) {
    int i = 0;
    while (i < texto.length() - 1 && texto.charAt(i) == '0') {
      i++;
    }
    return texto.substring(i);
  }
}
