
package com.example.damtr1g6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private SearchView searchView;
    ImageButton bottomsheet;
    List<Productos> products;
    static int userid;
    static int comandaid;
    static String userNom="";

    @SuppressLint("MissingInflatedId")
    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        userid = intent.getIntExtra("userID", userid);
        comandaid = intent.getIntExtra("comandaID", comandaid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        bottomsheet = findViewById(R.id.bottom_sheet);
        bottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.perfilmenu:
                        Intent intent1 = new Intent(MainActivity.this, Perfil.class);
                        intent1.putExtra("userID",userid);
                        startActivity(intent1);
                        return true;
                    case R.id.sesiomenu:
                        showLogoutConfirmationDialog();
                        return true;
                    case R.id.lastPedidos:
                        Intent intent2 = new Intent(MainActivity.this,LastOrder.class);
                        intent2.putExtra("userid",userid);
                        startActivity(intent2);
                        return true;
                }

                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        int espacioEntreObjetosEnDp = 16;
        int espacioEnPixeles = (int) (espacioEntreObjetosEnDp * getResources().getDisplayMetrics().density);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int column = position % 2;

                outRect.left = column * espacioEnPixeles / 2;
                outRect.right = espacioEnPixeles - (column + 1) * espacioEnPixeles / 2;
                outRect.bottom = espacioEnPixeles;
            }
        });
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Productos> resultados = filtrarProductos(query);
                productAdapter.setProducts(resultados);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Productos> resultados = filtrarProductos(newText);
                productAdapter.setProducts(resultados);
                return true;
            }
        });
        Log.d("USER", "ID: " + userid);
        mSocket.connect();

        mSocket.emit("getProductes", 0);
        mSocket.on("productes", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Type listType = new TypeToken<List<Productos>>() {}.getType();
                Gson gson = new Gson();
                final List<Productos> newProducts = gson.fromJson(args[0].toString(), listType);
                Log.d("cacacacacaaccaccac", "call: "+newProducts.get(0).getNom());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        products = new ArrayList<>();

                        for (Productos p : newProducts) {
                            if ("Disponible".equals(p.getEstado())) {
                                products.add(p);
                            }
                        }
                        productAdapter.setProducts(products);
                        productAdapter.setUserID(userid);
                    }
                });
            }
        });
    }

    private List<Productos> filtrarProductos(String query) {

        List<Productos> resultados = new ArrayList<>();
        for (Productos producto : products) {
            if (producto.getNom().toLowerCase().contains(query.toLowerCase())) {
                resultados.add(producto);
            }
        }
        return resultados;
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión")
                .setMessage("¿Estás seguro de que quieres cerrar sesión?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet);
        dialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height =1000;
        dialog.getWindow().setAttributes(layoutParams);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerViewBottomSheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSocket.connect();
        mSocket.emit("getComandaByIDInProcess", userid);
        mSocket.on("comanda", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Type listType = new TypeToken<List<OrderProcess>>() {}.getType();
                Gson gson = new Gson();
                final List<OrderProcess> newComandasInProcess = gson.fromJson(args[0].toString(), listType);
                Log.d("asas", "call: "+newComandasInProcess.get(0).getEstado());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newComandasInProcess != null) {
                            OrderProcessAdapter adapter = new OrderProcessAdapter(newComandasInProcess);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        });

    }

    private void logout() {
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void launchCarrito(View view) {
        Intent intent = new Intent(MainActivity.this, Carrito.class);
        intent.putExtra("userID", userid);
        Log.d("USER", "ID: " + userid);
        startActivity(intent);

    }

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://tastybyte.dam.inspedralbes.cat:3673/");
        } catch (URISyntaxException e) {}
    }

}
