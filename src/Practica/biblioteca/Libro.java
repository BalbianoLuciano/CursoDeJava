package Practica.biblioteca;

public class Libro {
  private static final double PRECIO_POR_DEFECTO = 15000.0;

  private final String titulo;
  private final String autor;
  private final String isbn;
  private int copiasDisponibles;
  private double precioReposicion;

  public Libro(String titulo, String autor, String isbn, int copiasDisponibles, double precioReposicion) {
    if (titulo == null || titulo.isBlank()) {
      System.out.println("Título inválido, se usó \"Sin título\" por defecto.");
      titulo = "Sin título";
    }
    if (autor == null || autor.isBlank()) {
      System.out.println("Autor inválido, se usó \"Autor desconocido\" por defecto.");
      autor = "Autor desconocido";
    }
    if (isbn == null || isbn.isBlank()) {
      System.out.println("ISBN inválido, se usó \"ISBN pendiente\" por defecto.");
      isbn = "ISBN pendiente";
    }
    if (copiasDisponibles < 0) {
      System.out.println("Copias negativas (" + copiasDisponibles + "), se usó 0 por defecto.");
      copiasDisponibles = 0;
    }
    this.titulo = titulo;
    this.autor = autor;
    this.isbn = isbn;
    this.copiasDisponibles = copiasDisponibles;
    if (!setPrecioReposicion(precioReposicion)) {
      System.out.println("Precio inválido (" + precioReposicion + "), se usó $" + PRECIO_POR_DEFECTO
          + " por defecto.");
      this.precioReposicion = PRECIO_POR_DEFECTO;
    }
  }

  public Libro(String titulo, String autor, String isbn) {
    this(titulo, autor, isbn, 1, PRECIO_POR_DEFECTO);
  }

  // new Libro(); // no compila: al declarar constructores propios, Java ya no
  // genera el constructor sin argumentos por defecto.

  public String getTitulo() {
    return titulo;
  }

  public String getAutor() {
    return autor;
  }

  public String getIsbn() {
    return isbn;
  }

  public int getCopiasDisponibles() {
    return copiasDisponibles;
  }

  public double getPrecioReposicion() {
    return precioReposicion;
  }

  // Misma regla que el constructor: el precio debe ser mayor a 0
  public boolean setPrecioReposicion(double precio) {
    if (precio <= 0) {
      return false;
    }
    this.precioReposicion = precio;
    return true;
  }

  public boolean prestar() {
    if (copiasDisponibles == 0) {
      System.out.println("Error: no hay copias disponibles de \"" + titulo + "\" para prestar.");
      return false;
    }
    copiasDisponibles--;
    System.out.println("Préstamo registrado: \"" + titulo + "\". Copias disponibles: " + copiasDisponibles);
    return true;
  }

  public void devolver() {
    copiasDisponibles++;
    System.out.println("Devolución registrada: \"" + titulo + "\". Copias disponibles: " + copiasDisponibles);
  }

  public void mostrarFicha() {
    System.out.println("=== Ficha de libro ===");
    System.out.println("Título:  " + titulo);
    System.out.println("Autor:   " + autor);
    System.out.println("ISBN:    " + isbn);
    System.out.println("Copias disponibles: " + copiasDisponibles);
    System.out.println("Precio de reposición: $" + precioReposicion);
    System.out.println("=======================");
  }
}
