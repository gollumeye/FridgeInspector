package com.example.fridgeinspector.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fridgeinspector.Category;
import com.example.fridgeinspector.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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
        binding.drinksButton.setY((int)(screen_height-screen_height/2.4));

        binding.milchprodukteButton.setX((int)(screen_width/1.6));
        binding.milchprodukteButton.setY((int)(screen_height-screen_height/1.75));

        binding.sonstigesButton.setX((int) (screen_width/1.6));
        binding.sonstigesButton.setY((int) (screen_height-screen_height/1.4));

        binding.closeButton.setOnClickListener(e->{
            binding.categoryListView.animate()
                    .alpha(0f)
                    .setDuration(getResources().getInteger(
                            android.R.integer.config_shortAnimTime))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            binding.categoryListView.setVisibility(View.GONE);
                        }
                    });
            binding.closeButton.setVisibility(View.GONE);
        });

        binding.fruitButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.FRUITS;
            listFragment.setTitle();
            });


        binding.fishButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.FISH;
            listFragment.setTitle();
        });

        binding.vegetablesButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.VEGETABLES;
            listFragment.setTitle();
        });

        binding.sweetsButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.SWEETS;
            listFragment.setTitle();
        });

        binding.sonstigesButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.OTHERS;
            listFragment.setTitle();
        });

        binding.meatButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.MEAT_AND_SAUSAGES;
            listFragment.setTitle();
        });

        binding.milchprodukteButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.DAIRY_PRODUCTS;
            listFragment.setTitle();
        });

        binding.iceButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.FROZEN;
            listFragment.setTitle();
        });

        binding.drinksButton.setOnClickListener(e->{
            showCategoryLists();
            CategoryListFragment listFragment = binding.categoryListView.getFragment();
            listFragment.category=Category.BEVERAGES;
            listFragment.setTitle();
        });

        return root;
    }

    public void showCategoryLists(){
        binding.categoryListView.bringToFront();
        binding.categoryListView.setAlpha(0f);
        binding.categoryListView.setVisibility(View.VISIBLE);

        binding.categoryListView.animate()
                .alpha(1f)
                .setDuration(getResources().getInteger(
                        android.R.integer.config_shortAnimTime))
                .setListener(null);

        binding.closeButton.bringToFront();
        binding.closeButton.setAlpha(0f);
        binding.closeButton.setVisibility(View.VISIBLE);

        binding.closeButton.animate()
                .alpha(1f)
                .setDuration(getResources().getInteger(
                        android.R.integer.config_shortAnimTime))
                .setListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}