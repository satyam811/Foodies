package com.example.foodies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodies.Adapters.MainAdapter;
import com.example.foodies.Models.MainModel;
import com.example.foodies.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
}