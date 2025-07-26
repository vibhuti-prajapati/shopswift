package com.example.shopswift;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeScreen extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        bottomNav = findViewById(R.id.bottomNav);

        // Load default fragment
        loadFragment(new homeFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = new homeFragment();
            } else if (itemId == R.id.nav_orders) {
                selectedFragment = new ordersFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new profileFragment();
            } else if (itemId == R.id.nav_products) {
                selectedFragment = new productsFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_fragment_container, fragment)
                .commit();
    }
}

