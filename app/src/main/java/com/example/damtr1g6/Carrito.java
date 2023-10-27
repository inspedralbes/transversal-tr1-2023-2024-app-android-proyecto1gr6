package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Carrito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        List<CartItem> cartItems = Cart.getInstance().getCartItems();

        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);
        CartAdapter cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
    }

    public void launchPaginaPrincipal(View view){
        Intent intent = new Intent(Carrito.this,MainActivity.class);
        startActivity(intent);
    }
}