package Practica.calculadorabase3;

/**
 * El número es demasiado grande para pasarlo a decimal con un long.
 * El mayor long es 9.223.372.036.854.775.807, que en base 3 tiene 40 dígitos;
 * con 39 dígitos o menos siempre entra.
 */
public class DesbordamientoException extends CalculadoraBase3Exception {
  private static final long serialVersionUID = 1L;

  public static final int MAXIMO_DIGITOS = 39;

  public DesbordamientoException(String numero) {
    super("El número tiene " + numero.replace("-", "").length() + " dígitos en base 3 y no entra en un long"
        + " (máximo " + MAXIMO_DIGITOS + " dígitos).");
  }
}
