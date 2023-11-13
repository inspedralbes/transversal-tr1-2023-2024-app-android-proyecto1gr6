package com.example.damtr1g6;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int ID;
    private Productos product;
    private int quantity;
    private double totalPrice;

    public CartItem(int ID,Productos product, int quantity) {
        this.ID = ID;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getID() {
        return ID;
    }
}
