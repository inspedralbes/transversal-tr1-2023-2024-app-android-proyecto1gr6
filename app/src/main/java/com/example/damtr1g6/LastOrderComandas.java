package com.example.damtr1g6;

import java.util.List;

public class LastOrderComandas {
    private int id_comanda;
    private List<LastOrderProduct> productos;

    public LastOrderComandas(int id_comanda, List<LastOrderProduct> productos) {
        this.id_comanda = id_comanda;
        this.productos = productos;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public List<LastOrderProduct> getProductos() {
        return productos;
    }
}
