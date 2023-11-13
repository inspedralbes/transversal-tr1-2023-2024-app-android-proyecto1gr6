package com.example.damtr1g6;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {
    private List<Usuarios> usuarios;

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perfil, parent, false);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        if (usuarios != null && position < usuarios.size()) {
            Usuarios usuario = usuarios.get(position);
            holder.bind(usuario);
        }
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }

    static class PerfilViewHolder extends RecyclerView.ViewHolder {
        private EditText emailEditText, usuarioEditText, rolEditText, tarjetaEditText, passwdEditText;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            emailEditText = itemView.findViewById(R.id.editTextEmail);
            usuarioEditText = itemView.findViewById(R.id.editTextUsuario);
            rolEditText = itemView.findViewById(R.id.editTextRol);
            tarjetaEditText = itemView.findViewById(R.id.editTextTarjeta);
            passwdEditText = itemView.findViewById(R.id.editTextPasswd);
        }

        public void bind(Usuarios usuario) {
            emailEditText.setText(usuario.getEmail());
            usuarioEditText.setText(usuario.getUsuario());
            rolEditText.setText(usuario.getRol());
            tarjetaEditText.setText(usuario.getTarjeta());
            passwdEditText.setText(usuario.getPasswd());
        }
    }
}