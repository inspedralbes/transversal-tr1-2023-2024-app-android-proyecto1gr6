package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
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
        userid = intent.getIntExtra("userID", userid);
        Log.d("USER", "ID: " + userid);

        items = Cart.getInstance().getCartItems();
        for (CartItem item : items) {
            Log.d("asasa", "sasas: " + item.getID());
        }
        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);
        CartAdapter cartAdapter = new CartAdapter(this, items);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
    }

    public void launchPaginaPrincipal(View view) {
        Intent intent = new Intent(Carrito.this, MainActivity.class);
        startActivity(intent);
    }

    public void launchComanda(View view) {
        if (items.isEmpty()) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.custom_toast_container));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        } else {

            double precioTotal = 0;
            for (CartItem item : items) {
                precioTotal += item.getProduct().getPreu() * item.getQuantity();
            }
            Intent intent = new Intent(Carrito.this, Comanda.class);
            intent.putExtra("productos", (Serializable) items);
            intent.putExtra("precioTotal", precioTotal);
            intent.putExtra("userId",userid);
            startActivity(intent);
        }
    }
}