package com.example.damtr1g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productDescriptionTextView;
    private TextView quantityTextView;
    private Button btnDecrease;
    private Button btnIncrease;
    private Button btnAddToCart;

    private Productos product;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImageView = findViewById(R.id.productImageView);
        productNameTextView = findViewById(R.id.productNameTextView);
        productDescriptionTextView = findViewById(R.id.productDescriptionTextView);
        quantityTextView = findViewById(R.id.quantityTextView);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        Intent intent = getIntent();
        if (intent.hasExtra("Product")) {
            Log.d("Entra", "Entra");
            product = (Productos) intent.getSerializableExtra("Product");
            Log.d("Producto", "Nom: "+product.getNom());
            Log.d("Producto", "Descripcio: "+product.getDescripcio());

            Picasso.get().load(product.getImagen()).into(productImageView);
            productNameTextView.setText(product.getNom());
            productDescriptionTextView.setText(product.getDescripcio());

            btnDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (quantity > 1) {
                        quantity--;
                        quantityTextView.setText(String.valueOf(quantity));
                    }
                }
            });

            btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity++;
                    quantityTextView.setText(String.valueOf(quantity));
                }
            });

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedQuantity = Integer.parseInt(quantityTextView.getText().toString());
                    CartItem cartItem = new CartItem(product, selectedQuantity);
                    Cart.getInstance().addItem(cartItem);
                    Intent intent = new Intent(ProductDetailActivity.this, Carrito.class);
                    intent.putExtra("cartItems", new ArrayList<>(Cart.getInstance().getCartItems()));
                    startActivity(intent);
                }
            });
        } else {
            Log.d("Entra", "No entra");
        }
    }
}
