package com.example.fridgeinspector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fridgeinspector.data.DataHandlingCategory;
import com.example.fridgeinspector.data.DataHandlingRecipe;
import com.example.fridgeinspector.databinding.ActivityMainBinding;
import com.example.fridgeinspector.ui.SettingsActivity;
import com.example.fridgeinspector.ui.recipes.RecipesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlertDialog dialog;
    private AlertDialog addRecipeDialog;
    private static int themeColor = 0;
    private Item item;
    private Recipe recipe;
    private String ingredient_list_text;
    private String name;
    private String textDescription;
    private EditText nameEditText;
    private String quantity;
    private EditText textViewDescription;
    private EditText ingredient_input;
    private RecipesFragment.RecipesRecyclerviewAdapter recipeAdapter;
    private Context context;
    private TextView ingredient_list;

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        DataHandlingCategory dhc = new DataHandlingCategory(context);
        DataHandlingRecipe dhr = new DataHandlingRecipe(context);


        switch (getThemeColor()) {
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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
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

        bAdd.setOnClickListener(view -> {
            String name = eName.getText().toString();

            if (name.equals("")) {
                Toast.makeText(
                                MainActivity.this,
                                "Error - Adding!",
                                Toast.LENGTH_SHORT)
                        .show();
            } else {
                String category = sCategory.getSelectedItem().toString();
                String quantity = sQuantity.getSelectedItem().toString();
                Calendar calendar = new GregorianCalendar(dExpirationDate.getYear(),
                        dExpirationDate.getMonth(),
                        dExpirationDate.getDayOfMonth());
                Date expirationDate = calendar.getTime();

                Calendar currentCalendar = Calendar.getInstance();
                Date currentDate = currentCalendar.getTime();
                long timeDifference = expirationDate.getTime() - currentDate.getTime();
                if (timeDifference < 0) {
                    timeDifference = 0;
                }

                Intent intent = new Intent(getApplicationContext(), Notifications.class);
                intent.putExtra("NAME", name);
                intent.putExtra("TIME_DIFF", Long.toString(timeDifference));
                startService(intent); //set Notification

                item = new Item(name, dhc.getCategory(category), expirationDate, Integer.parseInt(quantity));
                dhc.addNewFood(item);
                View.OnClickListener undoOnClickListener = view12 -> {
                    dhc.removeFoodItem(item.getName());
                    Snackbar.make(binding.getRoot(), "Item removed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                };

                Snackbar.make(binding.getRoot(), "Item added!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", undoOnClickListener).show();
            }

            dialog.dismiss();
        });

        bCancel.setOnClickListener(view1 -> dialog.dismiss());
        builder.setView(viewAddDialog);
        dialog = builder.create();

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Enter Recipe Data:");
        View viewAddDialog2 = getLayoutInflater().inflate(R.layout.add_recipe_dialog, null);
        nameEditText = (EditText) viewAddDialog2.findViewById(R.id.editTextName);
        Button addRecipe, addIngredient, cancelRecipeButton;
        addRecipe = viewAddDialog2.findViewById(R.id.addRecipeButton);
        addIngredient = viewAddDialog2.findViewById(R.id.addIngredientButton);
        Spinner spinner = (Spinner) viewAddDialog2.findViewById(R.id.spinner2);

        textViewDescription = (EditText) viewAddDialog2.findViewById(R.id.editTextDescription);

        addIngredient.setOnClickListener(e -> {
            quantity = spinner.getSelectedItem().toString();
            ingredient_input = (EditText) viewAddDialog2.findViewById(R.id.addIngredientInput);

            String new_ingredient = ingredient_input.getText().toString();
            ingredient_input.setText("");

            if (!new_ingredient.equals("")) {
                ingredient_list = viewAddDialog2.findViewById(R.id.ingredientListTextView);
                ingredient_list_text = ingredient_list.getText().toString();
                ingredient_list_text = ingredient_list_text + "\n" + quantity + "x " + new_ingredient;
                ingredient_list.setText(ingredient_list_text);
            }
        });
        cancelRecipeButton = viewAddDialog2.findViewById(R.id.button3);

        addRecipe.setOnClickListener(view -> {
            if (!nameEditText.getText().toString().trim().equals("")) {
                name = nameEditText.getText().toString().trim();
            }

            if (textViewDescription != null) {
                textDescription = textViewDescription.getText().toString().trim();
            }

            if (name != null && ingredient_list_text != null && textDescription != null) {
                recipe = new Recipe(name, ingredient_list_text, textDescription);
                dhr.addNewRecipe(recipe);
                View.OnClickListener undoOnClickListener = view14 -> {
                    dhr.removeRecipe(recipe.getName());
                    Snackbar.make(binding.getRoot(), "Recipe removed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                };

                Snackbar.make(binding.getRoot(), "Recipe added! Switch Fragment to refresh list!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", undoOnClickListener).show();
            } else {
                Snackbar.make(binding.getRoot(), "Error adding - something is missing!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
            textViewDescription.setText("");
            ingredient_list.setText("");
            nameEditText.setText("");
            addRecipeDialog.dismiss();
        });

        cancelRecipeButton.setOnClickListener(view2 -> addRecipeDialog.dismiss());
        builder2.setView(viewAddDialog2);
        addRecipeDialog = builder2.create();

        FloatingActionButton fab = findViewById(R.id.add_fab);
        switch (getThemeColor()) {
            case 0:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_blue));
                break;
            case 1:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_rot));
                break;
            case 2:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_green));
                break;
            case 3:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_purple));
                break;
            case 4:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_orange));
                break;
            case 5:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_gelb));
                break;
            case 6:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_brown));
                break;
            case 7:
                fab.setBackgroundTintList(getResources().getColorStateList(R.color.light_gray));
                break;
        }
        fab.setOnClickListener(view -> {
            int id = Objects.requireNonNull(navController.getCurrentDestination()).getId();
            if (id == R.id.navigation_home) {
                dialog.show();
            } else if (id == R.id.navigation_dashboard) {
                addRecipeDialog.show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static int getThemeColor() {
        return themeColor;
    }

    public static void setThemeColor(int themeColor) {
        MainActivity.themeColor = themeColor;
    }


}