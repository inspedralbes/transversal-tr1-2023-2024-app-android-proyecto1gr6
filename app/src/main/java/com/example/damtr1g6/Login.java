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


public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView uno = findViewById(R.id.imagelogoLogin);
        uno.setImageResource(R.drawable.logo);
    }
    public void launchPaginaPrincipal(View view){
        EditText nom = findViewById(R.id.editTextTextLogNom);
        EditText contrasenya = findViewById(R.id.editTextTextLogContra);

        String texto = nom.getText().toString().trim();
        String texto2 = contrasenya.getText().toString().trim();

        if (texto.isEmpty() || texto2.isEmpty()){
            if (texto.isEmpty()) {
                nom.setError("Campo obligatorio");
            }
            if (texto2.isEmpty()) {
                contrasenya.setError("Campo obligatorio");
            }

        } else {
            Log.d("Entra", "launchPaginaPrincipal: ");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.205.76:3672/usuarios/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiInterface = retrofit.create(ApiService.class);

            Call<List<Usuarios>> call = apiInterface.getUsuarios();
            call.enqueue(new Callback<List<Usuarios>>() {
                @Override
                public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                    if (response.isSuccessful()) {
                        List<Usuarios> datos = response.body();
                        boolean encontrado = false;
                        for (Usuarios u:datos) {
                            Log.d("USER", nom.getText().toString());
                            Log.d("pass", contrasenya.getText().toString());
                            if(u.getUsuario().equals(nom.getText().toString()) && u.getPasswd().equals(contrasenya.getText().toString())){
                                encontrado = true;
                            }
                            else{
                            }
                        }
                        if(encontrado){
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            TextView contra = findViewById(R.id.textLogNoContra);
                            contra.setVisibility(View.VISIBLE);
                            TextView nom = findViewById(R.id.textLogNoNom);
                            nom.setVisibility(View.VISIBLE);
                            Log.d("NO", "ENCONTRADO NO");
                        }
                    }
                    else {
                    }
                }
                @Override
                public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                    Log.d("daffla", "falla: " + t.getMessage());
                }
            });


        }
    }
    public void launchRegister(View view){
        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);
    }
}