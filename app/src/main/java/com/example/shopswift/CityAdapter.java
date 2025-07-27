package com.example.shopswift;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final List<String> cityList;
    private int selectedPosition = -1;
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public CityAdapter(Context context, List<String> cities) {
        this.context = context;
        this.cityList = cities;
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedCity = sharedPreferences.getString("selected_city", null);
        if (savedCity != null) {
            selectedPosition = cityList.indexOf(savedCity);
        }
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        String city = cityList.get(position);
        TextView textView = (TextView) holder.itemView;
        textView.setText(city);
        textView.setTextSize(16f);
        textView.setTextColor(Color.DKGRAY);
        textView.setPadding(16, 32, 16, 32);
        textView.setBackgroundColor(position == selectedPosition ? Color.LTGRAY : Color.TRANSPARENT);

        textView.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("selected_city", city);
            editor.apply();


            Toast.makeText(context, city + " selected", Toast.LENGTH_SHORT).show();
        });
        holder.itemView.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedCity", cityList.get(position));
            ((Activity) context).setResult(Activity.RESULT_OK, resultIntent);
            ((Activity) context).finish();
        });

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
