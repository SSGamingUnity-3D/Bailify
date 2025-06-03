package com.example.excusegenerator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GeneratedExcuseActivity extends AppCompatActivity {
    private TextView excuseTextView;
    private RatingBar ratingBar;
    private Button saveButton;
    private ImageButton backButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_excuse);

        excuseTextView = findViewById(R.id.generated_excuse_text);
        ratingBar = findViewById(R.id.generated_excuse_rating);
        saveButton = findViewById(R.id.save_generated_button);
        backButton = findViewById(R.id.back_button);
        dbHelper = new DatabaseHelper(this);

        String excuse = getIntent().getStringExtra("excuse_text");
        if (excuse != null) {
            excuseTextView.setText(excuse);
        }

        saveButton.setOnClickListener(view -> {
            float rating = ratingBar.getRating();
            if (excuse != null && rating > 0) {
                String category = "Unknown";  // You might want to pass category via intent or adjust accordingly
                boolean inserted = dbHelper.insertFavoriteExcuse(excuse, category, rating);
                if (inserted) {
                    Toast.makeText(this, "Saved to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to save favorite", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please add a rating before saving", Toast.LENGTH_SHORT).show();
            }
        });
        // Handle back button
        backButton.setOnClickListener(v -> onBackPressed());
    }
}

