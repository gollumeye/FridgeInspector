package com.example.fridgeinspector;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fridgeinspector.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Food Data:");
        View viewAddDialog = getLayoutInflater().inflate(R.layout.add_dialog, null);
        EditText eName = viewAddDialog.findViewById(R.id.name);
        Spinner sCategory, sQuantity;
        sCategory = viewAddDialog.findViewById(R.id.spinnerCategory);
        sQuantity = viewAddDialog.findViewById(R.id.spinnerQuantity);
        DatePicker dExpirationDate = viewAddDialog.findViewById(R.id.datePicker);
        Button bAdd, bCancel;
        bAdd = viewAddDialog.findViewById(R.id.addButton);
        bCancel = viewAddDialog.findViewById(R.id.cancelButton);

        bAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = eName.getText().toString();
                String category = sCategory.getSelectedItem().toString();
                String quantity = sQuantity.getSelectedItem().toString();
                Calendar calendar = new GregorianCalendar(dExpirationDate.getYear(),
                        dExpirationDate.getMonth(),
                        dExpirationDate.getDayOfMonth());
                Date expirationDate = calendar.getTime();
                //Item item = new Item(name, )
                System.out.println(name+" "+category+" "+quantity);
                dialog.dismiss();
            }
        });

        bCancel.setOnClickListener(view1 -> dialog.dismiss());
        builder.setView(viewAddDialog);
        dialog = builder.create();

        FloatingActionButton fab = findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Snackbar.make(view, "Adding Item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}