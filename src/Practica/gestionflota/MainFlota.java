package Practica.gestionflota;

public class MainFlota {
  public static void main(String[] args) {
    final double distanciaKm = 150.0;
    final String separador = "-".repeat(50);

    // arreglo polimórfico: el tipo declarado es Vehiculo, pero cada objeto es de su subclase
    Vehiculo[] flota = new Vehiculo[3];
    flota[0] = new Camion("AA123BB", "Scania", 450.0, 18.0);
    flota[1] = new Furgoneta("AF456CD", "Mercedes-Benz", 400.0, true);
    flota[2] = new MotoEnvios("A099XYZ", "Honda", 180.0);

    System.out.println("=== Reporte de Operaciones de Flota ===");
    double costoTotal = 0;
    for (Vehiculo v : flota) {
      // sin if, switch ni instanceof: Java elige en ejecución el método de la clase real (despacho dinámico)
      v.mostrarFicha();
      double costo = v.calcularCostoViaje(distanciaKm);
      System.out.println("Costo de viaje (" + distanciaKm + " km): $" + costo);
      System.out.println(separador);
      costoTotal += costo;
    }
    System.out.println("Costo total operativo de la flota: $" + costoTotal);

    // sobrecarga: con dos argumentos se usa la versión que suma peajes
    double peajes = 3500.0;
    System.out.println();
    System.out.println("=== Sobrecarga: mismo viaje del camión con peajes ===");
    System.out.println("Sin peajes: $" + flota[0].calcularCostoViaje(distanciaKm));
    System.out.println("Con $" + peajes + " de peajes: $" + flota[0].calcularCostoViaje(distanciaKm, peajes));
  }
}
