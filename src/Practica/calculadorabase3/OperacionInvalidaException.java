package Practica.calculadorabase3;

/** La opción elegida en el menú no existe. */
public class OperacionInvalidaException extends CalculadoraBase3Exception {
  private static final long serialVersionUID = 1L;

  public OperacionInvalidaException(String entrada) {
    super("\"" + entrada + "\" no es una opción del menú: elegí 0, 1, 2 o 3.");
  }
}
