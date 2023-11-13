package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comanda extends AppCompatActivity {
    static int id;
    static ArrayList<CartItem> items;
    static String fecha = "";
    static String dateMessage="";
    static String timeMessage="";

    public void dataRecollida(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    public void horaRecollida(View view){
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        dateMessage = (day_string + "/"
                + month_string + "/" + year_string);
        TextView selectedDateTextView = findViewById(R.id.checkedTextData);
        selectedDateTextView.setText(getString(R.string.selected_date, dateMessage));
    }
    public void processTimePickerResult(int hora, int minutos) {
        String horaString = String.format(Locale.getDefault(), "%02d:%02d", hora, minutos);
        timeMessage = getString(R.string.selected_time, horaString);
        TextView selectedTimeTextView = findViewById(R.id.checkedTextTime);
        selectedTimeTextView.setText(timeMessage);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        Intent intent = getIntent();
        items = (ArrayList<CartItem>) intent.getSerializableExtra("productos");
        double precioTotal = intent.getDoubleExtra("precioTotal", 0.0);
        id = intent.getIntExtra("userId",0);

        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        ComandaAdapter adapter = new ComandaAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        TextView precioTotalTextView = findViewById(R.id.precioTotalTextView);
        precioTotalTextView.setText("Precio Total: " + precioTotal + " €");
    }

    public void mandarPedido(View view){
        fecha+=dateMessage+"-"+timeMessage;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tastybyte.dam.inspedralbes.cat:3673/createComanda/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        ComandaDB comandaDB = new ComandaDB(id,fecha);
        Call<ID> call = apiService.getId(comandaDB);
        call.enqueue(new Callback<ID>() {
            @Override
            public void onResponse(Call<ID> call, Response<ID> response) {

                if (response.isSuccessful()) {
                    ID id = response.body();
                    Log.d("IDcomanda", "Comanda: " + id.getID());

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://tastybyte.dam.inspedralbes.cat:3673/insertProducte/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiService apiService = retrofit.create(ApiService.class);
                    List<Contiene> contienes = new ArrayList<>();
                    for (CartItem it : items) {
                        Contiene contiene = new Contiene(it.getID(), it.getQuantity(), id.getID());
                        contienes.add(contiene);
                    }
                    Call<List<Contiene>> call1 = apiService.insertComanda(contienes);
                    call1.enqueue(new Callback<List<Contiene>>() {

                        @Override
                        public void onResponse(Call<List<Contiene>> call, Response<List<Contiene>> response) {
                                Intent intent = new Intent(Comanda.this, MainActivity.class);
                                intent.putExtra("comandaID",id.getID());
                                Log.d("FECHA", "FECHA: "+fecha);
                                startActivity(intent);
                                fecha="";
                                Cart.getInstance().clearCart();

                        }

                        @Override
                        public void onFailure(Call<List<Contiene>> call, Throwable t) {
                            Log.d("Fallo", "Erros :"+t.getMessage());
                        }
                    });
                } else {

                }
            }

            @Override
            public void onFailure(Call<ID> call, Throwable t) {
                Log.d("Fallo", "Erros :"+t.getMessage());
            }
        });
    }

    public void launchCarrito(View view){
        Intent intent = new Intent(Comanda.this,Carrito.class);
        startActivity(intent);
    }
}