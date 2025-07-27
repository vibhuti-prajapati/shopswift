package com.example.shopswift;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class location_setting extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> cityList;
    String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_setting); // whatever your layout is named

        recyclerView = findViewById(R.id.recyclerViewCities);

        cityList = Arrays.asList(
                "Mumbai", "Bangalore", "Goa", "Kolkata", "Chennai", "Delhi-NCR", "Hyderabad", "Pune"
        );

        CityAdapter adapter = new CityAdapter(this, cityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedCity", selectedCity); // whatever the clicked city is
                setResult(RESULT_OK, resultIntent);
                finish(); // closes this activity and returns to the previous fragment/activity
            }
        });

    }


}
