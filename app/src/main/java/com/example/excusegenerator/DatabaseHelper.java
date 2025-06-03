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
        // School
        insertExcuse(db, "School", "I accidentally submitted my grocery list instead of my homework.");
        insertExcuse(db, "School", "I got stuck in a group project with imaginary friends.");
        insertExcuse(db, "School", "The school Wi-Fi banned my name.");
        insertExcuse(db, "School", "My textbook caught a virus.");
        insertExcuse(db, "School", "I wrote my essay in invisible ink.");
        insertExcuse(db, "School", "My backpack teleported to another dimension.");
        insertExcuse(db, "School", "I was practicing time travel and got stuck in 3025.");
        insertExcuse(db, "School", "My pencil unionized and went on strike.");
        insertExcuse(db, "School", "My online class turned into a cooking show by mistake.");
        insertExcuse(db, "School", "The dog ate my laptop... not just the homework.");

        // Work
        insertExcuse(db, "Work", "My office chair held me hostage.");
        insertExcuse(db, "Work", "The coffee machine started a rebellion.");
        insertExcuse(db, "Work", "I was updating my resume during a blackout.");
        insertExcuse(db, "Work", "The printer demanded a sacrifice.");
        insertExcuse(db, "Work", "I got stuck in traffic... inside a Zoom meeting.");
        insertExcuse(db, "Work", "My boss mistook me for an intern and fired me.");
        insertExcuse(db, "Work", "I slipped on a spreadsheet.");
        insertExcuse(db, "Work", "My work-from-home setup was taken over by squirrels.");
        insertExcuse(db, "Work", "Slack auto-replied 'on vacation' to everyone.");
        insertExcuse(db, "Work", "My calendar got hacked by Netflix.");

        // Tech Fail
        insertExcuse(db, "Tech Fail", "My smart fridge locked me out.");
        insertExcuse(db, "Tech Fail", "I asked Alexa to remind me, she just laughed.");
        insertExcuse(db, "Tech Fail", "Bluetooth married Wi-Fi, and they’re on a honeymoon.");
        insertExcuse(db, "Tech Fail", "Auto-correct translated my excuse to Latin.");
        insertExcuse(db, "Tech Fail", "I downloaded a bug instead of a file.");
        insertExcuse(db, "Tech Fail", "The cloud rained on my files.");
        insertExcuse(db, "Tech Fail", "I accidentally coded my door shut.");
        insertExcuse(db, "Tech Fail", "My phone ran away with my charger.");
        insertExcuse(db, "Tech Fail", "The keyboard was on vacation.");
        insertExcuse(db, "Tech Fail", "My AI assistant joined a startup.");

        // Pets
        insertExcuse(db, "Pets", "My cat scheduled a Zoom call.");
        insertExcuse(db, "Pets", "The dog is guarding my keys like treasure.");
        insertExcuse(db, "Pets", "My parrot told me to call in sick.");
        insertExcuse(db, "Pets", "My hamster stole my car keys.");
        insertExcuse(db, "Pets", "The fish unplugged the Wi-Fi.");
        insertExcuse(db, "Pets", "My lizard chewed through the power cable.");
        insertExcuse(db, "Pets", "I tripped over a turtle traffic jam.");
        insertExcuse(db, "Pets", "The pet sitter had a nervous breakdown.");
        insertExcuse(db, "Pets", "The bird mistook my phone for a snack.");
        insertExcuse(db, "Pets", "My pet rock rolled away with my ID.");

        // Aliens / Sci-fi
        insertExcuse(db, "Aliens / Sci-fi", "Aliens abducted me for a podcast interview.");
        insertExcuse(db, "Aliens / Sci-fi", "I got stuck in a wormhole during breakfast.");
        insertExcuse(db, "Aliens / Sci-fi", "Time travelers erased my morning.");
        insertExcuse(db, "Aliens / Sci-fi", "My clone refused to go to work.");
        insertExcuse(db, "Aliens / Sci-fi", "I got teleported to a moon meeting.");
        insertExcuse(db, "Aliens / Sci-fi", "A robot union blocked the elevators.");
        insertExcuse(db, "Aliens / Sci-fi", "I swapped bodies with a Martian.");
        insertExcuse(db, "Aliens / Sci-fi", "The AI overlords demanded a mental health day.");
        insertExcuse(db, "Aliens / Sci-fi", "Aliens mistook my car for their UFO.");
        insertExcuse(db, "Aliens / Sci-fi", "My hologram went rogue.");

        // Relationship
        insertExcuse(db, "Relationship", "My partner changed the clocks on purpose.");
        insertExcuse(db, "Relationship", "We were arguing over imaginary chores.");
        insertExcuse(db, "Relationship", "Date night turned into a debate club.");
        insertExcuse(db, "Relationship", "I got stuck on love island (literally).");
        insertExcuse(db, "Relationship", "We lost power trying to rekindle sparks.");
        insertExcuse(db, "Relationship", "The GPS led us to therapy.");
        insertExcuse(db, "Relationship", "We had a cuddle strike.");
        insertExcuse(db, "Relationship", "I got ghosted by Google Calendar.");
        insertExcuse(db, "Relationship", "Love potion caused a nap.");
        insertExcuse(db, "Relationship", "Our couple’s yoga turned into nap time.");

        // Family
        insertExcuse(db, "Family", "Grandma challenged me to Mario Kart.");
        insertExcuse(db, "Family", "My cousin locked me in the bathroom.");
        insertExcuse(db, "Family", "We had an emergency meeting about leftovers.");
        insertExcuse(db, "Family", "My siblings staged a pillow coup.");
        insertExcuse(db, "Family", "Aunt's karaoke blew the fuse.");
        insertExcuse(db, "Family", "Uncle Bob's story time ran overtime again.");
        insertExcuse(db, "Family", "We were searching for the TV remote for 3 hours.");
        insertExcuse(db, "Family", "Family group chat exploded—literally.");
        insertExcuse(db, "Family", "Mom hid my shoes as revenge.");
        insertExcuse(db, "Family", "Our dog and baby had a showdown.");

        // Fitness / Health
        insertExcuse(db, "Fitness / Health", "My yoga mat ran away.");
        insertExcuse(db, "Fitness / Health", "I sneezed and pulled a muscle.");
        insertExcuse(db, "Fitness / Health", "My protein shake exploded.");
        insertExcuse(db, "Fitness / Health", "My fitness tracker judged me.");
        insertExcuse(db, "Fitness / Health", "I went to the gym mentally.");
        insertExcuse(db, "Fitness / Health", "The treadmill tried to eat me.");
        insertExcuse(db, "Fitness / Health", "My new diet gave me hallucinations.");
        insertExcuse(db, "Fitness / Health", "Stretching turned into napping.");
        insertExcuse(db, "Fitness / Health", "I was recovering from burpee trauma.");
        insertExcuse(db, "Fitness / Health", "I got lost on my way to the gym (on purpose).");
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
