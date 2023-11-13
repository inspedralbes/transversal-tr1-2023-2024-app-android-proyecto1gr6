package com.example.damtr1g6;

import java.io.Serializable;
import java.util.List;

public class OrderProcess implements Serializable {
    private int id_comanda;
    private String estado_comanda;

    public OrderProcess(int id_comanda, String estado) {
        this.id_comanda = id_comanda;
        this.estado_comanda = estado;
    }

    public int getId_comandas() {
        return id_comanda;
    }
    public String getEstado() {
        return estado_comanda;
    }
}