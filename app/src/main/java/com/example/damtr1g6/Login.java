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
                nom.setError("Camp obligatori");
            }
            if (texto2.isEmpty()) {
                contrasenya.setError("Camp obligatori");
            }

        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://tastybyte.dam.inspedralbes.cat:3673/loginUser/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiInterface = retrofit.create(ApiService.class);
            Usuarios user = new Usuarios(texto,texto2);

            Call<Autoritzacio> call = apiInterface.getUsuarios(user);
            call.enqueue(new Callback<Autoritzacio>() {
                @Override
                public void onResponse(Call<Autoritzacio> call, Response<Autoritzacio> response) {
                    if (response.isSuccessful()) {
                        Autoritzacio datos = response.body();
                        boolean encontrado = datos.getAutoritzar();
                        if(encontrado){
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            intent.putExtra("userID",datos.getUserID());
                            intent.putExtra("userNom",texto);
                            startActivity(intent);
                        }else{
                            TextView contra = findViewById(R.id.textLogNoContra);
                            contra.setVisibility(View.VISIBLE);
                            TextView nom = findViewById(R.id.textLogNoNom);
                            nom.setVisibility(View.VISIBLE);

                        }
                    }
                    else {
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