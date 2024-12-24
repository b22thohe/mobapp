package com.example.playground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<RecyclerViewItem> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    RecyclerViewAdapter(Context context, ArrayList<RecyclerViewItem> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.email.setText(items.get(position).getEmail());
        holder.role.setText(items.get(position).getRole());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id;
        TextView login;
        TextView name;
        TextView email;
        TextView role;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            id = itemView.findViewById(R.id.tv_user_id);
            login = itemView.findViewById(R.id.tv_user_login);
            name = itemView.findViewById(R.id.tv_user_name);
            email = itemView.findViewById(R.id.tv_user_email);
            role = itemView.findViewById(R.id.tv_user_role);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(RecyclerViewItem item);
    }
}