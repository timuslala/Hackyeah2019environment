package com.example.hackyeah.ui.energy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hackyeah.R;

public class EnergyFragment extends Fragment {

    private EnergyViewModel energyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        energyViewModel =
                ViewModelProviders.of(this).get(EnergyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_energy, container, false);

        energyViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}