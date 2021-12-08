package com.example.outlab9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "myDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE test ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type TEXT, "
                + "description TEXT, "
                + "date TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing for the time being
    }

    public boolean addEvent(EventModel event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("type", event.getType());
        cv.put("description", event.getDescription());
        cv.put("date", event.getDate());

        long insert = db.insert("test", null, cv);

        return (insert >= 0);
    }


    public ArrayList<EventModel> getWithType(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<EventModel> answer = new ArrayList<EventModel>();
        String queryString = "SELECT * FROM test WHERE type = '" + type + "'";
        Cursor cursor = db.rawQuery(queryString, null);

        if (!cursor.moveToFirst()) {
            return null;
        }

        do {
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            EventModel em = new EventModel(type, description, date);
            answer.add(em);
        } while(cursor.moveToNext());

        return answer;
    }

    public ArrayList<EventModel> getWithDate(String d) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<EventModel> answer = new ArrayList<EventModel>();
        String queryString = "SELECT * FROM test WHERE date = '" + d + "'";
        Cursor cursor = db.rawQuery(queryString, null);

        if (!cursor.moveToFirst()) {
            return null;
        }

        do {
            String type = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            EventModel em = new EventModel(type, description, date);
            answer.add(em);
        } while(cursor.moveToNext());

        return answer;
    }

    public ArrayList<EventModel> getWithTypeDate(String t, String d) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<EventModel> answer = new ArrayList<EventModel>();
        String queryString = "SELECT * FROM test WHERE date = '" + d + "' AND type = '" + t + "'";
        Cursor cursor = db.rawQuery(queryString, null);

        if (!cursor.moveToFirst()) {
            return null;
        }

        do {
            String type = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            EventModel em = new EventModel(type, description, date);
            answer.add(em);
        } while(cursor.moveToNext());

        return answer;
    }
}
