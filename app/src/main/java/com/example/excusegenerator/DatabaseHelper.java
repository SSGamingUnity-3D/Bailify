package com.example.excusegenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "excuse_generator.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_EXCUSES = "excuses";
    private static final String TABLE_FAVORITES = "favorites";
    private static final String TABLE_CUSTOM_EXCUSES = "custom_excuses";

    // Common Columns
    private static final String COL_ID = "id";
    private static final String COL_CATEGORY = "category";
    private static final String COL_TEXT = "text";

    // Rating column
    private static final String COL_RATING = "rating";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Excuses table
        String createExcusesTable = "CREATE TABLE " + TABLE_EXCUSES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_CATEGORY + " TEXT," +
                COL_TEXT + " TEXT," +
                COL_RATING + " INTEGER DEFAULT 0)";
        db.execSQL(createExcusesTable);

        // Favorites table
        String createFavoritesTable = "CREATE TABLE " + TABLE_FAVORITES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                "excuse_text TEXT," +
                "category TEXT," +
                "rating REAL)";
        db.execSQL(createFavoritesTable);

        // Custom excuses table
        String createCustomExcusesTable = "CREATE TABLE " + TABLE_CUSTOM_EXCUSES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_CATEGORY + " TEXT," +
                COL_TEXT + " TEXT)";
        db.execSQL(createCustomExcusesTable);

        // Insert default excuses
        insertDefaultExcuses(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXCUSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOM_EXCUSES);
        onCreate(db);
    }

    // Insert default excuses to DB
    private void insertDefaultExcuses(SQLiteDatabase db) {
        insertExcuse(db, "School", "My hamster had a mental breakdown and needed emotional support.");
        insertExcuse(db, "School", "I got stuck in a YouTube loop researching how to say ‘I’m late’ in 42 languages.");
        insertExcuse(db, "Work", "My alarm clock joined a union and went on strike.");
        insertExcuse(db, "Aliens / Sci-fi", "Aliens abducted me but brought me back because I was too boring.");
    }

    // Helper to insert default excuse
    private void insertExcuse(SQLiteDatabase db, String category, String text) {
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORY, category);
        values.put(COL_TEXT, text);
        values.put(COL_RATING, 0);
        db.insert(TABLE_EXCUSES, null, values);
    }

    // Insert a custom excuse
    public void insertCustomExcuse(String category, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORY, category);
        values.put(COL_TEXT, text);
        db.insert(TABLE_CUSTOM_EXCUSES, null, values);
    }

    // Insert a favorite excuse
    public boolean insertFavoriteExcuse(String excuseText, String category, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("excuse_text", excuseText);
        values.put("category", category);
        values.put("rating", rating);
        long result = db.insert(TABLE_FAVORITES, null, values);
        return result != -1;
    }
    public boolean deleteFavoriteExcuseByText(String excuseText) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_FAVORITES, "excuse_text = ?", new String[]{excuseText});
        return rowsDeleted > 0;
    }

    // Get all excuses by category (including default and custom)
    public ArrayList<String> getExcusesByCategory(String category) {
        ArrayList<String> excuses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query default excuses
        Cursor cursor = db.query(TABLE_EXCUSES,
                new String[]{COL_TEXT},
                COL_CATEGORY + "=?",
                new String[]{category},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                excuses.add(cursor.getString(cursor.getColumnIndexOrThrow(COL_TEXT)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Query custom excuses
        Cursor customCursor = db.query(TABLE_CUSTOM_EXCUSES,
                new String[]{COL_TEXT},
                COL_CATEGORY + "=?",
                new String[]{category},
                null, null, null);

        if (customCursor.moveToFirst()) {
            do {
                excuses.add(customCursor.getString(customCursor.getColumnIndexOrThrow(COL_TEXT)));
            } while (customCursor.moveToNext());
        }
        customCursor.close();

        return excuses;
    }

    // Get all favorite excuses
    public ArrayList<HashMap<String, String>> getAllFavoriteExcuses() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FAVORITES, null, null, null, null, null, COL_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                String excuse = cursor.getString(cursor.getColumnIndexOrThrow("excuse_text"));
                String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
                String rating = cursor.getString(cursor.getColumnIndexOrThrow("rating"));

                HashMap<String, String> map = new HashMap<>();
                map.put("excuse", excuse);
                map.put("info", "Category: " + category + " | Rating: " + rating);

                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
