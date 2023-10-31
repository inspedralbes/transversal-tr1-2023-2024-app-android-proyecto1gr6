package com.example.damtr1g6;

public class Contiene {
    private int idProducto;
    private int cantidad;
    private int idComanda;

    public Contiene(int id_producto, int cantidad, int id_comanda) {
        this.idProducto = id_producto;
        this.cantidad = cantidad;
        this.idComanda = id_comanda;
    }

    public int getId_producto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getId_comanda() {
        return idComanda;
    }
}
