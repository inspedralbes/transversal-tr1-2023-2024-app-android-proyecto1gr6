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
                    .baseUrl("http://192.168.205.153:3672/loginUser/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiInterface = retrofit.create(ApiService.class);
            Usuarios user = new Usuarios(texto,texto2);

            Call<Autoritzacio> call = apiInterface.getUsuarios(user);
            call.enqueue(new Callback<Autoritzacio>() {
                @Override
                public void onResponse(Call<Autoritzacio> call, Response<Autoritzacio> response) {
                    Log.d("Entra", "SI: ");
                    if (response.isSuccessful()) {
                        Log.d("Entra", "onResponse: ");
                        Autoritzacio datos = response.body();
                        boolean encontrado = datos.getAutoritzar();
                        Log.d("Encontrado", "Encontrado: "+encontrado);
                        Log.d("Nom", "Nom: "+texto);
                        Log.d("Contra", "Contra: "+texto2);
                        if(encontrado){
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            intent.putExtra("userID",datos.getUserID());
                            Log.d("USER", "ID: "+datos.getUserID());
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
                        Log.d("No", "No");
                    }
                }
                @Override
                public void onFailure(Call<Autoritzacio> call, Throwable t) {
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