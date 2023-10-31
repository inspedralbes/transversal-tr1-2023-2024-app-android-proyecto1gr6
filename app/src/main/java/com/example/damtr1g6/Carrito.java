package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Carrito extends AppCompatActivity {
    static List<CartItem> items;
    static int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        Intent intent = getIntent();
        userid = intent.getIntExtra("userID",userid);
        Log.d("USER", "ID: "+userid);

        items = Cart.getInstance().getCartItems();
        for (CartItem item:items) {
            Log.d("asasa", "sasas: "+item.getID());
        }
        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);
        CartAdapter cartAdapter = new CartAdapter(items);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
    }

    public void launchPaginaPrincipal(View view){
        Intent intent = new Intent(Carrito.this,MainActivity.class);
        startActivity(intent);
    }

    public void launchComanda(View view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.205.153:3672/createComanda/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        ComandaDB comandaDB = new ComandaDB(userid);
        Log.d("IDuser", "User: "+comandaDB.getId_user());
        Call<ID> call = apiService.getId(comandaDB);
        call.enqueue(new Callback<ID>() {
            @Override
            public void onResponse(Call<ID> call, Response<ID> response) {

                if (response.isSuccessful()) {
                    ID id = response.body();
                    Log.d("IDcomanda", "Comanda: "+id.getID());

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.205.153:3672/insertProducte/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiService apiService = retrofit.create(ApiService.class);
                    for (CartItem it:items) {
                        Contiene contiene = new Contiene(it.getID(),it.getQuantity(),id.getID());
                        Call<Contiene> call1 = apiService.insertComanda(contiene);
                        call1.enqueue(new Callback<Contiene>() {

                            @Override
                            public void onResponse(Call<Contiene> call, Response<Contiene> response) {

                            }

                            @Override
                            public void onFailure(Call<Contiene> call, Throwable t) {

                            }
                        });
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ID> call, Throwable t) {

            }
        });
        Intent intent = new Intent(Carrito.this,Comanda.class);
        startActivity(intent);
    }
}