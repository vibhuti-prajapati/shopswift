package com.example.shopswift;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link editFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ActivityResultLauncher<Intent> locationLauncher;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public editFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static editFragment newInstance(String param1, String param2) {
        editFragment fragment = new editFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        locationLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        String selectedCity = result.getData().getStringExtra("selectedCity");

                        // Save it to SharedPreferences (optional)
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("ShopSwiftPrefs", Context.MODE_PRIVATE);
                        sharedPref.edit().putString("city", selectedCity).apply();

                        // Update UI
                        TextView cityTextView = view.findViewById(R.id.selectedCityText);
                        cityTextView.setText(selectedCity);
                    }
                }
        );

        ImageButton locationArrow = view.findViewById(R.id.locationarrow);
        locationArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), location_setting.class);
                locationLauncher.launch(intent);
            }
        });


        return view;
    }
}