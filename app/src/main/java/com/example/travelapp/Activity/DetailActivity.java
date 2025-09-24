package com.example.travelapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.bumptech.glide.Glide;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.Domain.Item;
import com.example.travelapp.databinding.ActivityDetailBinding;
import com.example.travelapp.R;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private Item object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText(getString(R.string.price_format, object.getPrice()));
        binding.backBtn.setOnClickListener(v->finish());
        binding.bedTxt.setText(getString(R.string.bed_format, object.getBed()));
        binding.durationTxt.setText(object.getDuration());
        binding.distanceTxt.setText(object.getDistance());
        binding.descriptionTxt.setText(object.getDescription());
        binding.addressTxt.setText(object.getAddress());
        binding.ratingBar.setRating((float) object.getScore());
        binding.ratingTxt.setText(getString(R.string.rating_format, object.getScore()));

        Glide.with(DetailActivity.this)
                .load(object.getPic())
                .into(binding.pic);

        binding.addToCartBtn.setOnClickListener(v -> {
            Intent intent=new Intent(DetailActivity.this,TicketActivity.class);
            intent.putExtra("object",object);
            startActivity(intent);
        });
    }

    private void getIntentExtra() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            object = getIntent().getParcelableExtra("object", Item.class);
        }
    }
}