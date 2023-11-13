package com.example.damtr1g6;

public class LastOrderProduct {
    private String nombre;
    private float precio;

    public LastOrderProduct(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }
}
