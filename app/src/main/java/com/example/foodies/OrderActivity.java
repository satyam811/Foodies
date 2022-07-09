package com.example.foodies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.foodies.Adapters.OrdersAdapter;
import com.example.foodies.Models.OrdersModel;
import com.example.foodies.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<OrdersModel> list = new ArrayList<>();
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));
        list.add(new OrdersModel(R.drawable.burger, "Chees Burgur", "59", "1234884"));


        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);


    }
}