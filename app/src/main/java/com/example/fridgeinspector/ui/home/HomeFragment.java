package com.example.fridgeinspector.ui.home;

import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fridgeinspector.MainActivity;
import com.example.fridgeinspector.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.categoryListView.setVisibility(View.GONE);
        binding.closeButton.setVisibility(View.GONE);
        binding.categoryListView.bringToFront();
        binding.fruitButton.bringToFront();
        binding.fishButton.bringToFront();
        binding.drinksButton.bringToFront();
        binding.iceButton.bringToFront();
        binding.meatButton.bringToFront();
        binding.milchprodukteButton.bringToFront();
        binding.sonstigesButton.bringToFront();
        binding.sweetsButton.bringToFront();
        binding.vegetablesButton.bringToFront();

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float screenAdjustment = (float) dm.densityDpi/160f;
        float screen_width = (float) dm.widthPixels;
        float screen_height = (float) dm.heightPixels;

        binding.vegetablesButton.setX((int) (screen_width/3.9));
        binding.vegetablesButton.setY((int) (screen_height-(screen_height/2.4)));

        binding.meatButton.setX((int) (screen_width/5.8));
        binding.meatButton.setY((int) (screen_height-(screen_height/1.95)));

        binding.fishButton.setX((int) (screen_width/3.2));
        binding.fishButton.setY((int) (screen_height-(screen_height/1.7)));

        binding.sweetsButton.setX((int) (screen_width/5.8));
        binding.sweetsButton.setY((int) (screen_height-(screen_height/1.48)));

        binding.fruitButton.setX((int)(screen_width/3.2));
        binding.fruitButton.setY((int)(screen_height-(screen_height/1.32)));

        binding.iceButton.setX((int)(screen_width/3.9));
        binding.iceButton.setY((int)(screen_height-(screen_height/1.1)));

        binding.drinksButton.setX((int)(screen_width/1.6));
        binding.drinksButton.setY((int)(screen_height-screen_height/2.3));

        binding.milchprodukteButton.setX((int)(screen_width/1.6));
        binding.milchprodukteButton.setY((int)(screen_height-screen_height/1.75));

        binding.sonstigesButton.setX((int) (screen_width/1.6));
        binding.sonstigesButton.setY((int) (screen_height-screen_height/1.4));


        //binding.vegetablesButton.setX((int) (100 * screenAdjustment));
        //binding.vegetablesButton.setY((int) (410 * screenAdjustment));

        //binding.meatButton.setX((int) (70 * screenAdjustment));
        //binding.meatButton.setY((int) (342 * screenAdjustment));

        //binding.fishButton.setX((int) (120 * screenAdjustment));
        //binding.fishButton.setY((int) (275 * screenAdjustment));

        //binding.sweetsButton.setX((int) (70 * screenAdjustment));
        //binding.sweetsButton.setY((int) (215 * screenAdjustment));

        //binding.fruitButton.setX((int) (120 * screenAdjustment));
        //binding.fruitButton.setY((int) (155 * screenAdjustment));

        //binding.drinksButton.setX((int) (260 * screenAdjustment));
        //binding.drinksButton.setY((int) (400 * screenAdjustment));

        //binding.milchprodukteButton.setX((int) (266 * screenAdjustment));
        //binding.milchprodukteButton.setY((int) (283 * screenAdjustment));

        //binding.sonstigesButton.setX((int) (265 * screenAdjustment));
        //binding.sonstigesButton.setY((int) (170 * screenAdjustment));

        //binding.iceButton.setX((int) (105 * screenAdjustment));
        //binding.iceButton.setY((int) (50 * screenAdjustment));


        binding.closeButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.GONE);
            binding.closeButton.setVisibility(View.GONE);
        });

        binding.fruitButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.fishButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.vegetablesButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.sweetsButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.sonstigesButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.meatButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });
        binding.milchprodukteButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.iceButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });

        binding.drinksButton.setOnClickListener(e->{
            binding.categoryListView.setVisibility(View.VISIBLE);
            binding.categoryListView.bringToFront();
            binding.closeButton.setVisibility(View.VISIBLE);
            binding.closeButton.bringToFront();
        });






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}