package Practica.biblioteca;

public class MainBiblioteca {
  public static void main(String[] args) {
    // Constructor de conveniencia: 1 copia y precio por defecto
    Libro cleanCode = new Libro("Clean Code", "Robert C. Martin", "9780132350884");

    // Constructor canónico con datos válidos
    Libro refactoring = new Libro("Refactoring", "Martin Fowler", "9780134757599", 3, 32000.0);

    // Rechazo 1: datos inválidos en la construcción
    Libro sinDatos = new Libro("   ", null, "9789875665436", -4, -500.0);

    // Rechazo 2: precio inválido con el setter
    double precioAnterior = refactoring.getPrecioReposicion();
    boolean aceptado = refactoring.setPrecioReposicion(-100.0);
    System.out.println("¿Se aceptó el precio -100.0? " + aceptado
        + " (se mantiene el precio anterior: $" + precioAnterior + ")");

    System.out.println();
    cleanCode.mostrarFicha();
    refactoring.mostrarFicha();
    sinDatos.mostrarFicha();

    System.out.println();
    // Agotar copias: Clean Code tiene 1 sola
    boolean prestado = cleanCode.prestar();
    System.out.println("¿Préstamo realizado? " + prestado);

    // Intento sin copias: debe devolver false y el contador no queda negativo
    prestado = cleanCode.prestar();
    System.out.println("¿Préstamo realizado? " + prestado
        + " | Copias disponibles: " + cleanCode.getCopiasDisponibles());

    cleanCode.devolver();

    System.out.println();
    // Refactoring tiene 3 copias: el 4to intento debe fallar
    for (int i = 1; i <= 4; i++) {
      System.out.println("Intento " + i + " -> ¿Préstamo realizado? " + refactoring.prestar());
    }
    System.out.println("Copias de \"" + refactoring.getTitulo() + "\": " + refactoring.getCopiasDisponibles());
  }
}
