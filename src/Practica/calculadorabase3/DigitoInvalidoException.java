package Practica.calculadorabase3;

/** El número tiene un carácter que no es 0, 1 ni 2. Guarda cuál y dónde está. */
public class DigitoInvalidoException extends NumeroInvalidoException {
  private static final long serialVersionUID = 1L;

  private final int posicion;

  public DigitoInvalidoException(String texto, int posicion) {
    super("El carácter '" + texto.charAt(posicion) + "' (posición " + (posicion + 1)
        + ") no existe en base 3: solo se usan los dígitos 0, 1 y 2.", texto);
    this.posicion = posicion;
  }

  public int getPosicion() {
    return posicion;
  }

  public char getCaracter() {
    return getTexto().charAt(posicion);
  }
}
