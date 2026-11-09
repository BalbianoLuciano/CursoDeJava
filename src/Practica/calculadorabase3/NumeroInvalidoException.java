package Practica.calculadorabase3;

/** El texto ingresado no es un número en base 3. */
public class NumeroInvalidoException extends CalculadoraBase3Exception {
  private static final long serialVersionUID = 1L;

  private final String texto;

  public NumeroInvalidoException(String mensaje, String texto) {
    super(mensaje);
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }
}
