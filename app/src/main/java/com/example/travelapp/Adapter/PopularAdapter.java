package com.example.travelapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.travelapp.Activity.DetailActivity;
import com.example.travelapp.Domain.Item;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelapp.databinding.ViewholderPopularBinding;

import java.util.ArrayList;
import com.example.travelapp.R;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<Item> items;
    Context context;

    public PopularAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderPopularBinding binding = ViewholderPopularBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        Item currentItem = items.get(position);

        holder.binding.titleTxt.setText(currentItem.getTitle());
        holder.binding.priceTxt.setText(context.getString(R.string.price_format, currentItem.getPrice()));
        holder.binding.addressTxt.setText(currentItem.getAddress());
        holder.binding.scoreTxt.setText(context.getString(R.string.score_format, currentItem.getScore()));

        Glide.with(context)
                .load(currentItem.getPic())
                .into(holder.binding.pic);

        holder.itemView.setOnClickListener(view -> {
            int adapterPosition = holder.getBindingAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(adapterPosition));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        ViewholderPopularBinding binding;

        public Viewholder(ViewholderPopularBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
