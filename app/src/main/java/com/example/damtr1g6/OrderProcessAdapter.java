package com.example.damtr1g6;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class OrderProcessAdapter extends RecyclerView.Adapter<OrderProcessAdapter.OrderProcessViewHolder> {
    private List<OrderProcess> comandas;

    public OrderProcessAdapter(List<OrderProcess> comandas) {
        this.comandas = comandas;
    }

    @NonNull
    @Override
    public OrderProcessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_process, parent, false);
        return new OrderProcessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProcessViewHolder holder, int position) {
        OrderProcess comanda = comandas.get(position);
        holder.bind(comanda);

        Button btnRecollir = holder.btnRecollir;
        if (comanda.getEstado().equals("Preparada")) {
            btnRecollir.setVisibility(View.VISIBLE);
        } else {
            btnRecollir.setVisibility(View.GONE);
        }

        btnRecollir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSocket.connect();
                try {
                    JSONObject jsonComanda = new JSONObject();
                    jsonComanda.put("id", comanda.getId_comandas());
                    jsonComanda.put("state", "Recollida");

                    mSocket.emit("changeState", jsonComanda);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.on("comandas", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return comandas != null ? comandas.size() : 0;
    }

    public static class OrderProcessViewHolder extends RecyclerView.ViewHolder {
        private TextView idComandaTextView;
        private TextView estadoComandaTextView;
        private Button btnRecollir;

        public OrderProcessViewHolder(@NonNull View itemView) {
            super(itemView);
            idComandaTextView = itemView.findViewById(R.id.textViewIdLastOrder);
            estadoComandaTextView = itemView.findViewById(R.id.textViewEstadoLastOrder);
            btnRecollir = itemView.findViewById(R.id.btnRecollir);
        }

        public void bind(OrderProcess comanda) {
            idComandaTextView.setText(String.valueOf(comanda.getId_comandas()));
            estadoComandaTextView.setText(comanda.getEstado());
        }
    }

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://tastybyte.dam.inspedralbes.cat:3673/");
        } catch (URISyntaxException e) {}
    }
}

