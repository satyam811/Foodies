package com.example.foodies;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodies.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    TextView txtRating;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtRating = (TextView) findViewById(R.id.txtRate);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                txtRating.setText(""+v);
            }
        });

        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type",0) == 1) {

            //reciving data form mainAdapter
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");


            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.nameLbl.setText(name);
            binding.detailDescription.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description
                          //  Integer.parseInt(binding.quantity.getText().toString())

                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Order Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //update detail
        }else{
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            final int images = cursor.getInt(4);

            binding.detailImage.setImageResource(images);
            binding.priceLbl.setText(String.format("%d",cursor.getInt(3)));
            binding.nameLbl.setText(cursor.getString(5));  //6
            binding.detailDescription.setText(cursor.getString(6));  //5
            //quantity
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.upadetOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            images,
                            binding.detailDescription.getText().toString(),
                            binding.nameLbl.getText().toString(),
                            id
                    );

                    if(isUpdated){
                        Toast.makeText(DetailActivity.this,"Order Updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DetailActivity.this,"Faild", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
}