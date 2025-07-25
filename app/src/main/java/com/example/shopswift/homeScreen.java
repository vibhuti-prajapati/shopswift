package com.example.shopswift;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Already on home
                return true;
            } else if (itemId == R.id.nav_orders) {
                startActivity(new Intent(homeScreen.this, OrdersActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(homeScreen.this, ProfileActivity.class));
                return true;
            }

            return false;
        });


    }

}
