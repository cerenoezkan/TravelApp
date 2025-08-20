package com.example.travelapp.Activity;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.travelapp.Adapter.RecommendedAdapter;
import com.example.travelapp.Domain.Item;
import com.example.travelapp.Adapter.PopularAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.example.travelapp.Adapter.CategoryAdapter;
import com.example.travelapp.Adapter.SliderAdapter;
import com.example.travelapp.Domain.Category;
import com.example.travelapp.Domain.Location;
import com.example.travelapp.Domain.SliderItems;
import com.example.travelapp.R;
import com.example.travelapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();

        // Verileri başlat
        initLocations();
        initBanners();
        initCategory();
        initPopular();
    }

    /**
     * Firebase'den konum listesini alır ve Spinner'a ekler
     */
    private void initRecommended() {
        DatabaseReference myRef = database.getReference("Item");
        ArrayList<Item> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Item.class));
                    }

                    if(!list.isEmpty()) {
                        binding.recyclerViewRecommended.setLayoutManager(
                                new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false)
                        );
                        RecyclerView.Adapter<RecommendedAdapter.Viewholder> adapter = new RecommendedAdapter(list);
                        binding.recyclerViewRecommended.setAdapter(adapter);
                    }
                    binding.progressBarRecommended.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata logu eklenebilir
            }
        });
    }
    private void initPopular() {
        DatabaseReference myRef = database.getReference("Popular");
        ArrayList<Item> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Item.class));
                    }

                    if(!list.isEmpty()) {
                        binding.recyclerViewPopular.setLayoutManager(
                                new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false)
                        );
                        RecyclerView.Adapter<PopularAdapter.Viewholder> adapter = new PopularAdapter(list);
                        binding.recyclerViewPopular.setAdapter(adapter);
                    }
                    binding.progressBarPopular.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata logu eklenebilir
            }
        });
    }
    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));
                    }

                   if(!list.isEmpty()) {
                       binding.recyclerViewCategory.setLayoutManager(
                               new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false)
                       );
                       RecyclerView.Adapter<CategoryAdapter.Viewholder> adapter = new CategoryAdapter(list);
                               binding.recyclerViewCategory.setAdapter(adapter);
                   }
                   binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata logu eklenebilir
            }
        });
    }
    private void initLocations() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Location.class));
                    }

                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            R.layout.sp_item,
                            list
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata logu eklenebilir
            }
        });
    }

    /**
     * Slider için verileri ayarlar
     */
    private void banners(ArrayList<SliderItems> items) {
        binding.viewPager2.setAdapter(new SliderAdapter(items, binding.viewPager2));
        binding.viewPager2.setClipToPadding(false);
        binding.viewPager2.setClipChildren(false);
        binding.viewPager2.setOffscreenPageLimit(3);
        binding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        binding.viewPager2.setPageTransformer(compositePageTransformer);
    }

    /**
     * Firebase'den banner verilerini alır ve slider'a ekler
     */
    private void initBanners() {
        DatabaseReference myRef = database.getReference("Banner");
        binding.progressBarBanner.setVisibility(VISIBLE);

        ArrayList<SliderItems> items = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        items.add(issue.getValue(SliderItems.class));
                    }
                    banners(items);
                    binding.progressBarBanner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata logu eklenebilir
            }
        });
    }
}
