package com.example.travelapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelapp.Domain.Category;
import com.example.travelapp.R;
import com.example.travelapp.databinding.ViewholderCategoryBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {

    private final List<Category> items;
    private Context context;

    // Seçim indeksleri (başlangıçta yok)
    private int selectedPosition = RecyclerView.NO_POSITION;
    private int lastSelectedPosition = RecyclerView.NO_POSITION;

    public CategoryAdapter(List<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderCategoryBinding binding =
                ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Viewholder holder, int ignored) {
        // Pozisyonu daima holder'dan al
        int adapterPosition = holder.getBindingAdapterPosition();
        if (adapterPosition == RecyclerView.NO_POSITION) return;

        Category item = items.get(adapterPosition);
        holder.binding.titleTxt.setText(item.getName());

        Glide.with(holder.itemView.getContext())
                .load(item.getImagePath())
                .into(holder.binding.pic);

        // Tıklama: pozisyonu o anda tekrar al
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;

                lastSelectedPosition = selectedPosition;
                selectedPosition = pos;

                if (lastSelectedPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(lastSelectedPosition);
                }
                notifyItemChanged(selectedPosition);
            }
        });

        // Görsel durum (seçili / seçili değil)
        boolean isSelected = (adapterPosition == selectedPosition);

        holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(context, R.color.white)
        );

        if (isSelected) {
            holder.binding.pic.setBackgroundResource(0);
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg);
            holder.binding.titleTxt.setVisibility(View.VISIBLE);
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.gray_bg);
            holder.binding.mainLayout.setBackgroundResource(0);
            holder.binding.titleTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() { return items.size(); }

    public static class Viewholder extends RecyclerView.ViewHolder {
        final ViewholderCategoryBinding binding;
        public Viewholder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
