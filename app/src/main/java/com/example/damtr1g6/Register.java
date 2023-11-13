package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView dos = findViewById(R.id.imagelogoLogin2);
        dos.setImageResource(R.drawable.logo);
    }



    public void launchPaginaPrincipal(View view){
        EditText nom = findViewById(R.id.editTextTextRegNom);
        EditText correu = findViewById(R.id.editTextTextRegCorreu);
        EditText contra = findViewById(R.id.editTextTextRegContra);
        EditText rol = findViewById(R.id.editTextTextRegRol);

        String texto = nom.getText().toString().trim();
        String texto2 = correu.getText().toString().trim();
        String texto3 = contra.getText().toString().trim();
        String texto4 = rol.getText().toString().trim();

        if (texto.isEmpty() || texto2.isEmpty() || texto3.isEmpty() || texto4.isEmpty()){
            if (texto.isEmpty()) {
                nom.setError("Camp obligatori");
            }
            if (texto2.isEmpty()) {
                correu.setError("Camp obligatori");
            }
            if (texto3.isEmpty()) {
                contra.setError("Camp obligatori");
            }
            if (texto4.isEmpty()){
                rol.setError("Camp obligatori");
            }

        } else {
            Usuarios u = new Usuarios(1,correu.getText().toString(),nom.getText().toString(),rol.getText().toString()," ",contra.getText().toString());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://tastybyte.dam.inspedralbes.cat:3673/usuario/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiInterface = retrofit.create(ApiService.class);

            Call<Usuarios> call = apiInterface.createUser(u);
            call.enqueue(new Callback<Usuarios>() {
                @Override
                public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }
                    else{
                    }
                }

                @Override
                public void onFailure(Call<Usuarios> call, Throwable t) {
                    Log.d("Fallo", t.getMessage());
                }
            });
        }
    }
    public void launchLogin(View view){
        Intent intent = new Intent(Register.this,Login.class);
        startActivity(intent);
    }
}