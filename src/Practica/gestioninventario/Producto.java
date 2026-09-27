package Practica.gestioninventario;

public class Producto {
  public String nombre;
  public String codigo;
  public double precio;
  public int stock;

  public void venderUnidades(int cantidad) {
    if (cantidad <= 0) {
      System.out.println("Error: la cantidad a vender debe ser positiva (" + cantidad + ").");
    } else if (cantidad > stock) {
      System.out.println("Error: stock insuficiente de " + nombre + ". Pedido: " + cantidad
          + ", disponible: " + stock + ".");
    } else {
      stock -= cantidad;
      System.out.println("Venta registrada: " + cantidad + " unidad(es) de " + nombre
          + ". Stock restante: " + stock);
    }
  }

  public void reponerStock(int cantidad) {
    if (cantidad <= 0) {
      System.out.println("Error: la cantidad a reponer debe ser positiva (" + cantidad + ").");
    } else {
      stock += cantidad;
      System.out.println("Reposición registrada: " + cantidad + " unidad(es) de " + nombre
          + ". Stock actual: " + stock);
    }
  }

  public void actualizarPrecio(double precio) {
    if (precio <= 0) {
      System.out.println("Error: el precio debe ser mayor a 0 (" + precio + ").");
      return;
    }
    double anterior = this.precio;
    this.precio = precio;
    System.out.println("Precio de " + nombre + " actualizado: $" + anterior + " -> $" + this.precio);
  }

  public void aplicarDescuento(double porcentaje) {
    if (porcentaje < 0 || porcentaje > 100) {
      System.out.println("Error: el descuento debe estar entre 0 y 100 (" + porcentaje + ").");
      return;
    }
    double anterior = precio;
    precio = precio - precio * porcentaje / 100;
    System.out.println("Descuento del " + porcentaje + "% aplicado a " + nombre + ": $"
        + anterior + " -> $" + precio);
  }

  public void mostrarFicha() {
    System.out.println("=== Ficha de producto ===");
    System.out.println("Nombre: " + nombre);
    System.out.println("Código: " + codigo);
    System.out.println("Precio: $" + precio);
    System.out.println("Stock:  " + stock);
    System.out.println("=========================");
  }
}
