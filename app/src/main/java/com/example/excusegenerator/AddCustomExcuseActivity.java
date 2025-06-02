package com.example.excusegenerator;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddCustomExcuseActivity extends AppCompatActivity {

    private Spinner customCategorySpinner;
    private EditText customExcuseInput;
    private Button saveExcuseButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom);

        dbHelper = new DatabaseHelper(this);

        customCategorySpinner = findViewById(R.id.custom_category_spinner);
        customExcuseInput = findViewById(R.id.custom_excuse_input);
        saveExcuseButton = findViewById(R.id.save_excuse_button);

        String[] categories = {
                "School", "Work", "Tech Fail", "Pets",
                "Aliens / Sci-fi", "Relationship", "Family", "Fitness / Health"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customCategorySpinner.setAdapter(adapter);

        saveExcuseButton.setOnClickListener(v -> {
            String selectedCategory = customCategorySpinner.getSelectedItem().toString();
            String customExcuse = customExcuseInput.getText().toString().trim();

            if (customExcuse.isEmpty()) {
                Toast.makeText(this, "Please enter an excuse.", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.insertCustomExcuse(selectedCategory, customExcuse);
                Toast.makeText(this, "Excuse saved!", Toast.LENGTH_SHORT).show();
                finish(); // Close this screen
            }
        });
    }
}
