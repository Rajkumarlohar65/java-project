package com.example.passwordmanager.ui.password;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordmanager.R;
import com.example.passwordmanager.data.model.Password;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder> {

    private List<Password> passwordList;

    public PasswordAdapter(List<Password> passwordList) {
        this.passwordList = passwordList;
    }

    public void setPasswords(List<Password> passwords) {
        this.passwordList = passwords;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_password, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (passwordList != null) {
            Password password = passwordList.get(position);
            holder.bind(password);
        }
    }

    @Override
    public int getItemCount() {
        return passwordList != null ? passwordList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView domainTextView;
        private TextView passwordTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            domainTextView = itemView.findViewById(R.id.editTextDomain);
            passwordTextView = itemView.findViewById(R.id.editTextPassword);
        }

        public void bind(Password password) {
            domainTextView.setText(password.getDomain());
            passwordTextView.setText(password.getPassword());
        }
    }
}
