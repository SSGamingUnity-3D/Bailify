package com.example.excusegenerator;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteExcusesActivity extends AppCompatActivity {

    private ListView favoritesListView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_excuses);

        favoritesListView = findViewById(R.id.favorites_list);
        dbHelper = new DatabaseHelper(this);

        ArrayList<HashMap<String, String>> favorites = dbHelper.getAllFavoriteExcuses();

//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                favorites,
//                android.R.layout.simple_list_item_2,
//                new String[]{"excuse", "info"},
//                new int[]{android.R.id.text1, android.R.id.text2}
//        );
//
//        favoritesListView.setAdapter(adapter);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                favorites,
                R.layout.item_favorite,  // Use custom layout here
                new String[]{"excuse", "info"},
                new int[]{R.id.text1, R.id.text2}
        );
        favoritesListView.setAdapter(adapter);
    }
}
