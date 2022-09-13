package com.example.foodies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodies.Adapters.MainAdapter;
import com.example.foodies.Adapters.SliderAdapter;
import com.example.foodies.Models.MainModel;
import com.example.foodies.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.drawer.Drawer;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ActivityMainBinding binding;
    //slider image
    SliderView sliderView;
    int[] images = {
            R.drawable.burger,
            R.drawable.sandwich,
            R.drawable.pizza,
            R.drawable.vadapav
    };

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.co.in/maps/dir//Pandesara,+Udhana,+Surat,+Gujarat+394220/@21.1563571,72.8180257,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x3be04e1e62a3676d:0x13755e8560cd5259!2m2!1d72.8228426!2d21.1557526?hl=en"));
                Intent chooser = Intent.createChooser(intent,"Launch Map");
                startActivity(chooser);
            }
        });

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

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
        list.add(new MainModel(R.drawable.momos, "Momos", "89", "Buy More then three pizza get 20 ruppes discount"));

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
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
            case R.id.share:
                Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_SUBJECT,"download this app");
                intent2.putExtra(Intent.EXTRA_TEXT,"http:/foodies/download");
                startActivity(Intent.createChooser(intent2,"share via"));
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart:
                Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),OrderActivity.class));
                break;
            case R.id.location:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.co.in/maps/dir//Pandesara,+Udhana,+Surat,+Gujarat+394220/@21.1563571,72.8180257,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x3be04e1e62a3676d:0x13755e8560cd5259!2m2!1d72.8228426!2d21.1557526?hl=en"));
                Intent chooser = Intent.createChooser(intent,"Launch Map");
                startActivity(chooser);
                Toast.makeText(this, "location", Toast.LENGTH_SHORT).show();
                break;
            case R.id.call:
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:6353403684"));
                startActivity(intent1);
                Toast.makeText(this, "call", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}