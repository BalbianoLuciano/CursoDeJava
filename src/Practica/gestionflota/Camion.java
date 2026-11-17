package Practica.gestionflota;

public class Camion extends Vehiculo {
  private double capacidadToneladas;

  public Camion(String patente, String marca, double costoBaseKm, double capacidadToneladas) {
    super(patente, marca, costoBaseKm);
    this.capacidadToneladas = capacidadToneladas;
  }

  @Override
  public double calcularCostoViaje(double distanciaKm) {
    // 5% extra por cada tonelada de capacidad
    return (distanciaKm * costoBaseKm) * (1 + capacidadToneladas * 0.05);
  }

  @Override
  public void mostrarFicha() {
    System.out.println("[Vehículo] Camión | Patente: " + patente + " | Marca: " + marca + " | Toneladas: " + capacidadToneladas);
  }
}
