package com.example.damtr1g6;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Productos product;
    private int quantity;
    private double totalPrice;

    public CartItem(Productos product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPreu() * quantity;
    }

    public Productos getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
