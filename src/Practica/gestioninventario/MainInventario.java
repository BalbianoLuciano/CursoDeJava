package Practica.gestioninventario;

public class MainInventario {
  public static void main(String[] args) {
    Producto yerba = new Producto();
    yerba.nombre = "Yerba Mate 1kg";
    yerba.codigo = "P001";
    yerba.precio = 4500.0;
    yerba.stock = 20;

    Producto azucar = new Producto();
    azucar.nombre = "Azúcar 1kg";
    azucar.codigo = "P002";
    azucar.precio = 1200.0;
    azucar.stock = 10;

    Producto cafe = new Producto();
    cafe.nombre = "Café 500g";
    cafe.codigo = "P003";
    cafe.precio = 6800.0;
    cafe.stock = 5;

    System.out.println("--- Estado inicial ---");
    yerba.mostrarFicha();
    azucar.mostrarFicha();
    cafe.mostrarFicha();

    System.out.println("\n--- Operaciones válidas ---");
    yerba.venderUnidades(3);
    azucar.reponerStock(15);
    cafe.actualizarPrecio(7200.0);
    yerba.aplicarDescuento(10);

    System.out.println("\n--- Operaciones inválidas ---");
    cafe.venderUnidades(50);
    cafe.venderUnidades(-2);
    azucar.reponerStock(0);
    yerba.actualizarPrecio(-100);
    yerba.aplicarDescuento(150);

    // Cada objeto tiene su propio estado: vender yerba no afectó al azúcar ni al café
    System.out.println("\n--- Independencia entre objetos ---");
    yerba.mostrarFicha();
    azucar.mostrarFicha();
    cafe.mostrarFicha();

    // Aliasing: otra variable apunta al MISMO objeto, no a una copia
    System.out.println("\n--- Aliasing ---");
    Producto alias = cafe;
    alias.venderUnidades(2);
    alias.actualizarPrecio(7500.0);
    System.out.println("¿alias == cafe? " + (alias == cafe));
    System.out.println("Ficha de cafe (modificado a través de alias):");
    cafe.mostrarFicha();

    System.out.println("\n--- Recorrido con arreglo ---");
    Producto[] inventario = { yerba, azucar, cafe };
    double valorTotal = 0;
    for (Producto p : inventario) {
      System.out.println(p.codigo + " - " + p.nombre + ": " + p.stock + " u. x $" + p.precio);
      valorTotal += p.stock * p.precio;
    }
    System.out.println("Valor total del inventario: $" + valorTotal);
  }
}
