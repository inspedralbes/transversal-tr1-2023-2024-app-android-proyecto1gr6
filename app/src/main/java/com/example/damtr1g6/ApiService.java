package com.example.damtr1g6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("http://tastybyte.dam.inspedralbes.cat:3673/loginUser/")
    Call<Autoritzacio> getUsuarios(@Body Usuarios loginuser);

    @POST("http://tastybyte.dam.inspedralbes.cat:3673/usuario/")
    Call<Usuarios> createUser(@Body Usuarios usuario);

    @GET("http://tastybyte.dam.inspedralbes.cat:3673/productos/")
    Call<List<Productos>> getProductos();

    @POST("http://tastybyte.dam.inspedralbes.cat:3673/createComanda/")
    Call <ID> getId(@Body ComandaDB comandaDB);

    @POST("http://tastybyte.dam.inspedralbes.cat:3673/insertProducte/")
    Call <List<Contiene>> insertComanda(@Body List<Contiene> contiene);

    @GET("http://tastybyte.dam.inspedralbes.cat:3673/usuarioID/{id}")
    Call<List<Usuarios>> getUserById(@Path("id") int userId);



}


