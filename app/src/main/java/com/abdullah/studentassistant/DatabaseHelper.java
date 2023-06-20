package com.abdullah.studentassistant;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lectures.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LECTURES_TABLE = "CREATE TABLE lectures (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "start_time TEXT, " +
                "end_time TEXT, " +
                "days TEXT)";
        db.execSQL(CREATE_LECTURES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lectures");
        onCreate(db);
    }

    // Add your database operations here (insert, update, delete, query)
    public void insertLecture(Lecture lecture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", lecture.getName());
        values.put("start_time", lecture.getStartTime());
        values.put("end_time", lecture.getEndTime());
        values.put("days", lecture.getDays());

        db.insert("lectures", null, values);
        db.close();
    }

    // Update an existing lecture in the database
    public void updateLecture(Lecture lecture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", lecture.getName());
        values.put("start_time", lecture.getStartTime());
        values.put("end_time", lecture.getEndTime());
        values.put("days", lecture.getDays());

        db.update("lectures", values, "id = ?", new String[]{String.valueOf(lecture.getId())});
        db.close();
    }

    // Delete a lecture from the database
    public void deleteLecture(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("lectures", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Query all lectures from the database
    public List<Lecture> getAllLectures() {
        List<Lecture> lectures = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("lectures", null, null, null, null, null, "start_time");

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String startTime = cursor.getString(cursor.getColumnIndex("start_time"));
                @SuppressLint("Range") String endTime = cursor.getString(cursor.getColumnIndex("end_time"));
                @SuppressLint("Range") String days = cursor.getString(cursor.getColumnIndex("days"));

                Lecture lecture = new Lecture(id, name, startTime, endTime, days);
                lectures.add(lecture);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lectures;
    }
}