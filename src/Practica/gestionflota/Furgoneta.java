package Practica.gestionflota;

public class Furgoneta extends Vehiculo {
  private static final double RECARGO_REFRIGERACION = 5000;

  private boolean tieneRefrigeracion;

  public Furgoneta(String patente, String marca, double costoBaseKm, boolean tieneRefrigeracion) {
    super(patente, marca, costoBaseKm);
    this.tieneRefrigeracion = tieneRefrigeracion;
  }

  @Override
  public double calcularCostoViaje(double distanciaKm) {
    // se reutiliza el cálculo de la superclase y se suma el recargo por cadena de frío
    double costo = super.calcularCostoViaje(distanciaKm);
    if (tieneRefrigeracion) {
      costo += RECARGO_REFRIGERACION;
    }
    return costo;
  }

  @Override
  public void mostrarFicha() {
    System.out.println("[Vehículo] Furgoneta | Patente: " + patente + " | Marca: " + marca + " | Refrigerado: "
        + (tieneRefrigeracion ? "Sí" : "No"));
  }
}
