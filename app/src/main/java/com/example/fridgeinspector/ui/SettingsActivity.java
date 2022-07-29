package com.example.fridgeinspector.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgeinspector.MainActivity;
import com.example.fridgeinspector.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {
    Button blueButton;
    Button redButton;
    Button yellowButton;
    Button purpleButton;
    Button greenButton;
    Button orangeButton;
    Button brownButton;
    Button grayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch(MainActivity.getThemeColor()){
            case 0:
                setTheme(R.style.BlueTheme);
                break;
            case 1:
                setTheme(R.style.RedTheme);
                break;
            case 2:
                setTheme(R.style.GreenTheme);
                break;
            case 3:
                setTheme(R.style.PurpleTheme);
                break;
            case 4:
                setTheme(R.style.OrangeTheme);
                break;
            case 5:
                setTheme(R.style.YellowTheme);
                break;
            case 6:
                setTheme(R.style.BrownTheme);
                break;
            case 7:
                setTheme(R.style.GrayTheme);
                break;
        }

        setContentView(R.layout.activity_settings);

        FloatingActionButton returnFab = findViewById(R.id.returnFAB);

        switch(MainActivity.getThemeColor()){
            case 0:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_blue));
                break;
            case 1:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_rot));
                break;
            case 2:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_green));
                break;
            case 3:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_purple));
                break;
            case 4:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_orange));
                break;
            case 5:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_gelb));
                break;
            case 6:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_brown));
                break;
            case 7:
                returnFab.setBackgroundTintList(getResources().getColorStateList(R.color.light_gray));
                break;
        }

        returnFab.setOnClickListener(e->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        blueButton = findViewById(R.id.buttonBlue);
        blueButton.setOnClickListener(e->{
            MainActivity.setThemeColor(0);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        redButton = findViewById(R.id.buttonRed);
        redButton.setOnClickListener(e->{
            MainActivity.setThemeColor(1);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        greenButton = findViewById(R.id.buttonGreen);
        greenButton.setOnClickListener(e->{
            MainActivity.setThemeColor(2);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        purpleButton = findViewById(R.id.buttonPurple);
        purpleButton.setOnClickListener(e->{
            MainActivity.setThemeColor(3);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        orangeButton = findViewById(R.id.buttonOrange);
        orangeButton.setOnClickListener(e->{
            MainActivity.setThemeColor(4);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        yellowButton = findViewById(R.id.buttonYellow);
        yellowButton.setOnClickListener(e->{
            MainActivity.setThemeColor(5);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        brownButton = findViewById(R.id.buttonBrown);
        brownButton.setOnClickListener(e->{
            MainActivity.setThemeColor(6);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        grayButton = findViewById(R.id.buttonGray);
        grayButton.setOnClickListener(e->{
            MainActivity.setThemeColor(7);
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });
    }
}