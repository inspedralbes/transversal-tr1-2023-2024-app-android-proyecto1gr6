package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView uno = findViewById(R.id.imageView);
        uno.setImageResource(R.drawable.ic_launcher_background);
        ImageView dos = findViewById(R.id.imageView2);
        dos.setImageResource(R.drawable.ic_launcher_background);
        ImageView tres = findViewById(R.id.imageView3);
        tres.setImageResource(R.drawable.ic_launcher_background);
        ImageView cuatro = findViewById(R.id.imageView4);
        cuatro.setImageResource(R.drawable.ic_launcher_background);
        ImageView cinco = findViewById(R.id.imageView5);
        cinco.setImageResource(R.drawable.ic_launcher_background);
        ImageView seis = findViewById(R.id.imageView6);
        seis.setImageResource(R.drawable.ic_launcher_background);
    }
}