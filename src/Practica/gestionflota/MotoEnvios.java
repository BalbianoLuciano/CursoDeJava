package Practica.gestionflota;

public class MotoEnvios extends Vehiculo {
  public MotoEnvios(String patente, String marca, double costoBaseKm) {
    super(patente, marca, costoBaseKm);
  }

  @Override
  public double calcularCostoViaje(double distanciaKm) {
    // 15% de descuento por transporte liviano
    return super.calcularCostoViaje(distanciaKm) * 0.85;
  }

  @Override
  public void mostrarFicha() {
    System.out.println("[Vehículo] MotoEnvios | Patente: " + patente + " | Marca: " + marca + " | Mensajería liviana");
  }
}
