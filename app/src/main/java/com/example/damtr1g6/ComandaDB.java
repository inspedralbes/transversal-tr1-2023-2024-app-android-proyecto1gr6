package com.example.damtr1g6;

public class ComandaDB {
    private int id_user;
    private String fecha;

    public ComandaDB(int id_user, String fecha) {
        this.id_user = id_user;
        this.fecha = fecha;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
