package com.example.damtr1g6;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<CartItem> cartItems;

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(CartItem cartItem) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == cartItem.getProduct().getId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                item.setTotalPrice(item.getProduct().getPreu() * item.getQuantity());
                return;
            }
        }
        cartItem.setTotalPrice(cartItem.getProduct().getPreu() * cartItem.getQuantity()); // Calcula el precio total
        cartItems.add(cartItem);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}