package Practica.calculadorabase3;

/**
 * Excepción base de la calculadora: todas las demás heredan de esta.
 * Extiende Exception (y no RuntimeException), así que es "controlada": Java obliga a
 * declararla con throws o a atraparla con catch.
 *
 * Jerarquía, de lo más general a lo más específico:
 *
 *   CalculadoraBase3Exception
 *   ├── NumeroInvalidoException
 *   │   ├── NumeroVacioException
 *   │   └── DigitoInvalidoException
 *   ├── OperacionInvalidaException
 *   └── DesbordamientoException
 */
public class CalculadoraBase3Exception extends Exception {
  // las excepciones son Serializable; este número identifica la versión de la clase
  private static final long serialVersionUID = 1L;

  public CalculadoraBase3Exception(String mensaje) {
    super(mensaje);
  }
}
