package com.example.damtr1g6;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LastOrderAdapter extends RecyclerView.Adapter<LastOrderAdapter.LastOrderViewHolder> {
    private List<LastOrderComandas> comandas;

    public LastOrderAdapter(List<LastOrderComandas> comandas) {
        this.comandas = comandas;
    }

    @NonNull
    @Override
    public LastOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_order, parent, false);
        return new LastOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastOrderViewHolder holder, int position) {
        LastOrderComandas comanda = comandas.get(position);
        holder.bind(comanda);
    }

    @Override
    public int getItemCount() {
        return comandas.size();
    }

    public static class LastOrderViewHolder extends RecyclerView.ViewHolder {
        private TextView idComandaTextView;
        private RecyclerView productosRecyclerView;
        private LastOrderProductAdapter productosAdapter;

        public LastOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idComandaTextView = itemView.findViewById(R.id.textViewIdComanda);
            productosRecyclerView = itemView.findViewById(R.id.recyclerViewProductos);
            productosAdapter = new LastOrderProductAdapter(new ArrayList<>());

            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            productosRecyclerView.setLayoutManager(layoutManager);
            productosRecyclerView.setAdapter(productosAdapter);
        }

        public void bind(LastOrderComandas comanda) {
            idComandaTextView.setText(String.valueOf(comanda.getId_comanda()));
            productosAdapter.setProductos(comanda.getProductos());
            productosAdapter.notifyDataSetChanged();
        }
    }
}


