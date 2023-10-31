package com.example.damtr1g6;

public class Usuarios {

    private int id;
    private String email;
    private String usuario;
    private String rol;
    private String tarjeta;
    private String passwd;

    public Usuarios(int id, String email, String usuario, String rol, String tarjeta, String passwd) {
        this.id = id;
        this.email = email;
        this.usuario = usuario;
        this.rol = rol;
        this.tarjeta = tarjeta;
        this.passwd = passwd;
    }
    public Usuarios (String usuario,String passwd){
        this.id = 0;
        this.email = "";
        this.usuario = usuario;
        this.rol = "";
        this.tarjeta = "";
        this.passwd = passwd;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getRol() {
        return rol;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public String getPasswd() {
        return passwd;
    }
}
