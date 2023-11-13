package com.example.damtr1g6;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LastOrderProductAdapter extends RecyclerView.Adapter<LastOrderProductAdapter.LastOrderProductViewHolder> {
    private List<LastOrderProduct> productos;

    public LastOrderProductAdapter(List<LastOrderProduct> productos) {
        this.productos = productos;
    }

    public void setProductos(List<LastOrderProduct> productos) {
        this.productos = productos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LastOrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_order_product, parent, false);
        return new LastOrderProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastOrderProductViewHolder holder, int position) {
        LastOrderProduct producto = productos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return  productos != null ? productos.size() : 0;
    }

    public static class LastOrderProductViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreTextView;
        private TextView precioTextView;

        public LastOrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.textViewNombreProducto);
            precioTextView = itemView.findViewById(R.id.textViewPrecioProducto);
        }

        public void bind(LastOrderProduct producto) {
            nombreTextView.setText(producto.getNombre());
            precioTextView.setText(String.valueOf(producto.getPrecio()));
        }
    }
}
