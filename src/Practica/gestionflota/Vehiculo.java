package Practica.gestionflota;

public class Vehiculo {
  protected String patente;
  protected String marca;
  protected double costoBaseKm;

  public Vehiculo(String patente, String marca, double costoBaseKm) {
    this.patente = patente;
    this.marca = marca;
    this.costoBaseKm = costoBaseKm;
  }

  public double calcularCostoViaje(double distanciaKm) {
    return distanciaKm * costoBaseKm;
  }

  // sobrecarga: mismo nombre, distintos parámetros; se elige al compilar.
  // Llama a la versión de un parámetro, así que respeta la sobrescritura de cada subclase.
  public double calcularCostoViaje(double distanciaKm, double peajes) {
    return calcularCostoViaje(distanciaKm) + peajes;
  }

  public void mostrarFicha() {
    System.out.println("[Vehículo] Patente: " + patente + " | Marca: " + marca + " | Costo base por km: $" + costoBaseKm);
  }
}
