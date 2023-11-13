// Generated by view binder compiler. Do not edit!
package com.example.damtr1g6.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.damtr1g6.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemCartBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageButton btnDecrease;

  @NonNull
  public final ImageButton btnDelete;

  @NonNull
  public final ImageButton btnIncrease;

  @NonNull
  public final TextView priceTextView;

  @NonNull
  public final TextView productNameTextView;

  @NonNull
  public final TextView quantityTextView;

  private ItemCartBinding(@NonNull LinearLayout rootView, @NonNull ImageButton btnDecrease,
      @NonNull ImageButton btnDelete, @NonNull ImageButton btnIncrease,
      @NonNull TextView priceTextView, @NonNull TextView productNameTextView,
      @NonNull TextView quantityTextView) {
    this.rootView = rootView;
    this.btnDecrease = btnDecrease;
    this.btnDelete = btnDelete;
    this.btnIncrease = btnIncrease;
    this.priceTextView = priceTextView;
    this.productNameTextView = productNameTextView;
    this.quantityTextView = quantityTextView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemCartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemCartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_cart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemCartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDecrease;
      ImageButton btnDecrease = ViewBindings.findChildViewById(rootView, id);
      if (btnDecrease == null) {
        break missingId;
      }

      id = R.id.btnDelete;
      ImageButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.btnIncrease;
      ImageButton btnIncrease = ViewBindings.findChildViewById(rootView, id);
      if (btnIncrease == null) {
        break missingId;
      }

      id = R.id.priceTextView;
      TextView priceTextView = ViewBindings.findChildViewById(rootView, id);
      if (priceTextView == null) {
        break missingId;
      }

      id = R.id.productNameTextView;
      TextView productNameTextView = ViewBindings.findChildViewById(rootView, id);
      if (productNameTextView == null) {
        break missingId;
      }

      id = R.id.quantityTextView;
      TextView quantityTextView = ViewBindings.findChildViewById(rootView, id);
      if (quantityTextView == null) {
        break missingId;
      }

      return new ItemCartBinding((LinearLayout) rootView, btnDecrease, btnDelete, btnIncrease,
          priceTextView, productNameTextView, quantityTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}