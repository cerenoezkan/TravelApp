package com.example.travelapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.travelapp.Domain.SliderItems;
import com.example.travelapp.R;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewholder> {
    private final ArrayList<SliderItems> sliderItems;
    private final ViewPager2 viewPager2;
    private Context context;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentSize = sliderItems.size();
            ArrayList<SliderItems> newItems = new ArrayList<>(sliderItems); // veya yeni listeden ekle
            sliderItems.addAll(newItems); // eklenen öğeler
            notifyItemRangeInserted(currentSize, newItems.size());
        }
    };

    public SliderAdapter(ArrayList<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapter.SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new SliderViewholder(LayoutInflater.from(context).inflate(R.layout.slider_item_container,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewholder holder, int position) {
    holder.setImage(sliderItems.get(position));
    if(position==sliderItems.size()-2){
        viewPager2.post(runnable);
    }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

      public class SliderViewholder extends RecyclerView.ViewHolder {
            private final ImageView imageView;
          public SliderViewholder(@NonNull View itemView) {
              super(itemView);
              imageView=itemView.findViewById(R.id.imageSlider);
          }
          void setImage(SliderItems sliderItems){
              Glide.with(context)
                      .load(sliderItems.getUrl())
                      .into(imageView);
          }
      }
}
