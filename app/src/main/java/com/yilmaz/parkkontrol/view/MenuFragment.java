package com.yilmaz.parkkontrol.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yilmaz.parkkontrolu.R;


public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView manualImage = view.findViewById(R.id.manualImage);
        ImageView autoImage = view.findViewById(R.id.autoImage);

        manualImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToManual(view);
            }
        });

        autoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAuto(view);
            }
        });
    }

    public void goToManual(View view){
        NavDirections action = MenuFragmentDirections.actionMenuFragmentToManualFragment();
        Navigation.findNavController(view).navigate(action);
    }

    public void goToAuto(View view){
        NavDirections action = MenuFragmentDirections.actionMenuFragmentToAutoFragment();
        Navigation.findNavController(view).navigate(action);
    }
}