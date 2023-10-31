package com.example.damtr1g6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("http://192.168.205.153:3672/loginUser/")
    Call<Autoritzacio> getUsuarios(@Body Usuarios loginuser);

    @POST("http://192.168.205.153:3672/usuario/")
    Call<Usuarios> createUser(@Body Usuarios usuario);

    @GET("http://192.168.205.153:3672/productos/")
    Call<List<Productos>> getProductos();

    @POST("http://192.168.205.153:3672/createComanda/")
    Call <ID> getId(@Body ComandaDB comandaDB);

    @POST("http://192.168.205.153:3672/insertProducte/")
    Call <Contiene> insertComanda(@Body Contiene contiene);


}


