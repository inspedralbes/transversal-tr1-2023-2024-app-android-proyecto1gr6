// Generated by view binder compiler. Do not edit!
package com.example.damtr1g6.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public final class CustomToastLayoutBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final LinearLayout customToastContainer;

  @NonNull
  public final ImageView logoImageView;

  @NonNull
  public final TextView messageTextView;

  private CustomToastLayoutBinding(@NonNull FrameLayout rootView,
      @NonNull LinearLayout customToastContainer, @NonNull ImageView logoImageView,
      @NonNull TextView messageTextView) {
    this.rootView = rootView;
    this.customToastContainer = customToastContainer;
    this.logoImageView = logoImageView;
    this.messageTextView = messageTextView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomToastLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomToastLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_toast_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomToastLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.custom_toast_container;
      LinearLayout customToastContainer = ViewBindings.findChildViewById(rootView, id);
      if (customToastContainer == null) {
        break missingId;
      }

      id = R.id.logoImageView;
      ImageView logoImageView = ViewBindings.findChildViewById(rootView, id);
      if (logoImageView == null) {
        break missingId;
      }

      id = R.id.messageTextView;
      TextView messageTextView = ViewBindings.findChildViewById(rootView, id);
      if (messageTextView == null) {
        break missingId;
      }

      return new CustomToastLayoutBinding((FrameLayout) rootView, customToastContainer,
          logoImageView, messageTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}