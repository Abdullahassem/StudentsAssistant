package com.abdullah.studentassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        Button AddNewSubjectButton;
        Button showScheduleButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main); // activity_main.xml
            // make a button that takes you to the add subject activity
            AddNewSubjectButton = findViewById(R.id.AddNewSubjectButton);
            AddNewSubjectButton.setOnClickListener(view -> {

                // go to the add subject activity
                Intent intent = new Intent(com.abdullah.studentassistant.MainActivity.this, AddLecture.class);
                startActivity(intent);
            });

            showScheduleButton = findViewById(R.id.showScheduleButton);
            showScheduleButton.setOnClickListener(view -> {
                Intent intent = new Intent(com.abdullah.studentassistant.MainActivity.this, ShowAllLecturesSchedule.class);
                startActivity(intent);
            });

        }
    }