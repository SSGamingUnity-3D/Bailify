package com.example.excusegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private Button generateButton, addCustomButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize views
        categorySpinner = findViewById(R.id.category_spinner);
        generateButton = findViewById(R.id.generate_button);
        addCustomButton = findViewById(R.id.add_custom_button);

        // Define categories
        String[] categories = {
                "School", "Work", "Tech Fail", "Pets",
                "Aliens / Sci-fi", "Relationship", "Family", "Fitness / Health"
        };

        // Set up spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Generate excuse button logic
        generateButton.setOnClickListener(v -> {
            String category = categorySpinner.getSelectedItem().toString();
            List<String> excuses = dbHelper.getExcusesByCategory(category);

            if (!excuses.isEmpty()) {
                // Pick a random excuse
                String generatedExcuse = excuses.get(new Random().nextInt(excuses.size()));

                // Launch next screen
                Intent intent = new Intent(MainActivity.this, GeneratedExcuseActivity.class);
                intent.putExtra("excuse_text", generatedExcuse);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "No excuses found for this category.", Toast.LENGTH_SHORT).show();
            }
        });

        // Add custom excuse button
        addCustomButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCustomExcuseActivity.class);
            startActivity(intent);
        });

        // View favorites button
        Button viewFavoritesButton = findViewById(R.id.view_favorites_button);
        viewFavoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteExcusesActivity.class);
            startActivity(intent);
        });
    }
}
