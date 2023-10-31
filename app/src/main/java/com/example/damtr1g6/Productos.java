package com.example.damtr1g6;

import java.io.Serializable;

public class Productos  implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen_url;
    private int stock;
    private String estado;

    public Productos(int id, String nom, String descripcio, float preu, String imagen, int stock, String estado) {
        this.id = id;
        this.nombre = nom;
        this.descripcion = descripcio;
        this.precio = preu;
        this.imagen_url = imagen;
        this.stock = stock;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nombre;
    }

    public String getDescripcio() {
        return descripcion;
    }

    public double getPreu() {
        return precio;
    }

    public String getImagen() {
        return imagen_url;
    }

    public int getStock() {
        return stock;
    }

    public String getEstado() {
        return estado;
    }
}
