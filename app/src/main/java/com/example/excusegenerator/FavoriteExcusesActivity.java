package com.example.excusegenerator;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteExcusesActivity extends AppCompatActivity {

    private ListView favoritesListView;
    private DatabaseHelper dbHelper;
    private FavoriteExcuseAdapter adapter;
    private ArrayList<HashMap<String, String>> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_excuses);

        favoritesListView = findViewById(R.id.favorites_list);
        dbHelper = new DatabaseHelper(this);

        favorites = dbHelper.getAllFavoriteExcuses();

        adapter = new FavoriteExcuseAdapter(this, favorites, dbHelper);
        favoritesListView.setAdapter(adapter);
    }
}
