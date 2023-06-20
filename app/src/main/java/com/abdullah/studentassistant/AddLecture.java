package com.abdullah.studentassistant;

import android.app.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLecture extends AppCompatActivity {

    private EditText etLectureName, etStartTime, etEndTime, etDays;
    private Button btnAddLecture;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);

        etLectureName = findViewById(R.id.etLectureName);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        etDays = findViewById(R.id.etDays);
        btnAddLecture = findViewById(R.id.btnAddLecture);

        dbHelper = new DatabaseHelper(this);

        btnAddLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etLectureName.getText().toString().trim();
                String startTime = etStartTime.getText().toString().trim();
                String endTime = etEndTime.getText().toString().trim();
                String days = etDays.getText().toString().trim();

                if (name.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || days.isEmpty()) {
                    Toast.makeText(AddLecture.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Lecture lecture = new Lecture(0, name, startTime, endTime, days);
                    dbHelper.insertLecture(lecture);
                    Toast.makeText(AddLecture.this, "Lecture added successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and return to the previous one
                }
            }
        });
    }
}
