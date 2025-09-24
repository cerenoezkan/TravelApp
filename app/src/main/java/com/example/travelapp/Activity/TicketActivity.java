package com.example.travelapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.travelapp.Domain.Item;
import com.example.travelapp.R;
import com.example.travelapp.databinding.ActivitySplashBinding;
import com.example.travelapp.databinding.ActivityTicketBinding;

public class TicketActivity extends AppCompatActivity {
    ActivityTicketBinding binding;
    private Item object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        // Tur resmi
        Glide.with(TicketActivity.this)
                .load(object.getPic())
                .into(binding.pic);
        // Rehber profil resmi
        Glide.with(TicketActivity.this)
                .load(object.getGetTourGuidePic())
                .into(binding.profile);
        // Geri butonu
        binding.backBtn.setOnClickListener(v->finish());
        // TextView’lere nesneden gelen bilgileri set etme
        binding.titleTxt.setText(object.getTitle());
        binding.durationTxt.setText(object.getDuration());
        binding.tourGuideNameTxt.setText(object.getGetTourGuideName());
        binding.timeTxt.setText(object.getTimeTour());
        binding.timeTxttourGuideTxt.setText(object.getDateTour());

        // SMS atma
        binding.callBtn.setOnClickListener(view -> {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:" + object.getTourGuidePhone()));
            sendIntent.putExtra("sms_body" ,"type your message");
            startActivity(sendIntent);
            // Telefonla arama
        binding.messageBtn.setOnClickListener(view1 -> {
            String phone=object.getTourGuidePhone();
            Intent intent=new Intent(Intent.ACTION_DIAL,
                    Uri.fromParts("tel",phone,null));
            startActivity(intent);
        });
        });
    }

    private void getIntentExtra() {
        object=(Item) getIntent().getSerializableExtra("object");
    }
}