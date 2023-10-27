package com.example.damtr1g6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("http://192.168.205.76:3672/usuarios/")
    Call<List<Usuarios>> getUsuarios();

    @POST("http://192.168.205.76:3672/usuario/")
    Call<Usuarios> createUser(@Body Usuarios usuario);

    @GET("http://192.168.205.76:3672/productos/")
    Call<List<Productos>> getProductos();


}


