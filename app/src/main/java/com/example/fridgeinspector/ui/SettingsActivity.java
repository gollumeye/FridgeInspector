package com.example.fridgeinspector.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fridgeinspector.MainActivity;
import com.example.fridgeinspector.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FloatingActionButton returnFab = findViewById(R.id.returnFAB);
        returnFab.setOnClickListener(e->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}