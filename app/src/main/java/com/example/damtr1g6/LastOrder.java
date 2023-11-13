package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LastOrder extends AppCompatActivity {
    static int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_order);
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid",userid);

        mSocket.connect();
        mSocket.emit("getComandaByID",userid);
        mSocket.on("comanda", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("TAG", "call: " +args[0]);
                Type listType = new TypeToken<List<LastOrderComandas>>() {}.getType();
                Gson gson = new Gson();
                final List<LastOrderComandas>newComands  = gson.fromJson(args[0].toString(), listType);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       LastOrderAdapter adapter = new LastOrderAdapter(newComands);
                RecyclerView recyclerView = findViewById(R.id.recyclerViewLastOrder);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);

                   }
               });
            }
        });
    }

    public void launchPaginaPrincipal(View view){
        Intent intent = new Intent(LastOrder.this,MainActivity.class);
        startActivity(intent);
    }

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://tastybyte.dam.inspedralbes.cat:3673/");
        } catch (URISyntaxException e) {}
    }
}
