package com.example.damtr1g6;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Productos> products;
    static int userID;

    public void setProducts(List<Productos> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setUserID(int userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Productos product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView;
        private TextView productNameTextView;
        private TextView productDescriptionTextView;
        private TextView productPriceTextView;
        private Button btnBuy;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            btnBuy = itemView.findViewById(R.id.btnBuy);
        }

        void bind(Productos product) {
            Picasso.get().load("http://tastybyte.dam.inspedralbes.cat:3673/api/images/"+product.getImagen()).into(productImageView);
            productNameTextView.setText(product.getNom());
            productDescriptionTextView.setText(product.getDescripcio());
            productPriceTextView.setText("Preu: " + String.valueOf(product.getPreu())+"€");


            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                    Log.d("IDPAA", "ID: "+userID);
                    intent.putExtra("Product", product);
                    intent.putExtra("userID",userID);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}