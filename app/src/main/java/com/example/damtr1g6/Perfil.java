package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {
    static int userid;
    private RecyclerView recyclerView;
    private List<Usuarios> usuariosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent intent = getIntent();
        userid = intent.getIntExtra("userID", userid);

        recyclerView = findViewById(R.id.perfilRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PerfilAdapter perfiladapter = new PerfilAdapter();
        recyclerView.setAdapter(perfiladapter);
        Log.d("TAG", "onResponse: estoy");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tastybyte.dam.inspedralbes.cat:3673/usuarioID/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiInterface = retrofit.create(ApiService.class);

        Call<List<Usuarios>> call = apiInterface.getUserById(userid);
        call.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>>response) {
                if (response.isSuccessful()) {
                    usuariosList = response.body();
                    Log.d("TAG", "onResponse: "+ usuariosList.get(0).getUsuario());
                    perfiladapter.setUsuarios(usuariosList);
                } else{
                    Log.d("TAG", "onResponse: fallo dentro response");
                }
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                Log.d("TAG", "onResponse: fallo");
            }
        });
    }
    public void launchPaginaPrincipal(View view){
        Intent intent = new Intent(Perfil.this,MainActivity.class);
        startActivity(intent);
    }
}