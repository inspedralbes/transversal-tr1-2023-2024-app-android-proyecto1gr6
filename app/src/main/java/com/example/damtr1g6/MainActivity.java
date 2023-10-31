
package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    static int userid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);

        Intent intent = getIntent();
        userid = intent.getIntExtra("userID",userid);
        Log.d("USER", "ID: "+userid);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.205.153:3672/productos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiInterface = retrofit.create(ApiService.class);

        Call<List<Productos>> call = apiInterface.getProductos();
        call.enqueue(new Callback<List<Productos>>() {
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                if (response.isSuccessful()){
                    List<Productos> products = response.body();
                    productAdapter.setProducts(products);
                    productAdapter.setUserID(userid);
                }
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                Log.d("falla", "falla productos");
            }
        });
    }

    public void launchCarrito(View view){
            Intent intent = new Intent(MainActivity.this, Carrito.class);
            intent.putExtra("userID",userid);
            Log.d("USER", "ID: "+userid);
            startActivity(intent);

    }
    public void launchPerfil(View view){
        Intent intent = new Intent(MainActivity.this,Perfil.class);
        startActivity(intent);
    }
}
