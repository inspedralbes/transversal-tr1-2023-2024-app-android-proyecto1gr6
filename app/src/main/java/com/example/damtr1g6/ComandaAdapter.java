package com.example.damtr1g6;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComandaAdapter extends RecyclerView.Adapter<ComandaAdapter.ProductoViewHolder> {
    private ArrayList<CartItem> productos;

    public ComandaAdapter(ArrayList<CartItem> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comanda, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        CartItem producto = productos.get(position);
        holder.nombreProductoTextView.setText(producto.getProduct().getNom());
        holder.cantidadProductoTextView.setText("x" + producto.getQuantity());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreProductoTextView;
        TextView cantidadProductoTextView;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProductoTextView = itemView.findViewById(R.id.nombreProductoTextView);
            cantidadProductoTextView = itemView.findViewById(R.id.cantidadProductoTextView);
        }
    }
}

