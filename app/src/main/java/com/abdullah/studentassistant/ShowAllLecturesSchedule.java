package com.abdullah.studentassistant;

import android.app.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowAllLecturesSchedule extends AppCompatActivity {

    private RecyclerView rvLecturesSchedule;
    private DatabaseHelper dbHelper;
    private LecturesScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_lectures_schedule);

        rvLecturesSchedule = findViewById(R.id.rvLecturesSchedule);
        dbHelper = new DatabaseHelper(this);

        List<Lecture> lectures = dbHelper.getAllLectures();
        Map<String, List<Lecture>> lecturesByDay = new HashMap<>();

        for (Lecture lecture : lectures) {
            String[] days = lecture.getDays().split(",");

            for (String day : days) {
                day = day.trim().toLowerCase();

                if (!lecturesByDay.containsKey(day)) {
                    lecturesByDay.put(day, new ArrayList<>());
                }

                lecturesByDay.get(day).add(lecture);
            }
        }

        adapter = new LecturesScheduleAdapter(lecturesByDay);
        rvLecturesSchedule.setLayoutManager(new LinearLayoutManager(this));
        rvLecturesSchedule.setAdapter(adapter);
    }
}