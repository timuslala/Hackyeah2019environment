package com.example.hackyeah.ui.waste;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hackyeah.R;

public class WasteFragment extends Fragment {

    private WasteViewModel wasteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wasteViewModel =
                ViewModelProviders.of(this).get(WasteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_waste, container, false);

        wasteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}