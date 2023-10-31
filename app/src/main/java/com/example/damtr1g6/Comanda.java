package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Comanda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
    }

    public void launchCarrito(View view){
        Intent intent = new Intent(Comanda.this,Carrito.class);
        startActivity(intent);
    }
}