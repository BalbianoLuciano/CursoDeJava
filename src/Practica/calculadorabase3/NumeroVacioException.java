package Practica.calculadorabase3;

/** No se ingresó nada. */
public class NumeroVacioException extends NumeroInvalidoException {
  private static final long serialVersionUID = 1L;

  public NumeroVacioException() {
    super("No ingresaste ningún número.", "");
  }
}
