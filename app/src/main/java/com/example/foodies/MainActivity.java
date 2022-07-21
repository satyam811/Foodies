package com.example.foodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.foodies.Adapters.MainAdapter;
import com.example.foodies.Adapters.SliderAdapter;
import com.example.foodies.Models.MainModel;
import com.example.foodies.databinding.ActivityMainBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    //slider image
    SliderView sliderView;
    int[] images = {
            R.drawable.burger,
            R.drawable.sandwich,
            R.drawable.pizza,
            R.drawable.vadapav
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //slider view
        sliderView = findViewById(R.id.slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Burger", "59", "Veg Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "99", "Veg Pizza With Extra Cheese"));
        list.add(new MainModel(R.drawable.sandwich, "Sandwich", "79", "Extra Cheese for Ruppes 20"));
        list.add(new MainModel(R.drawable.vadapav, "Vadapav", "49", "best padapav in all over surat"));
        list.add(new MainModel(R.drawable.momos, "Momos", "89", "Buy More then three piza get 20 ruppes discount"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}