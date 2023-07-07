package com.abdullah.studentassistant;

import android.app.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddLecture extends AppCompatActivity {

    private EditText etLectureName, etStartTime, etEndTime, etDays;
    private Button btnAddLecture;
    private DatabaseHelper dbHelper;
    private CheckBox[] checkBoxes = new CheckBox[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);

        etLectureName = findViewById(R.id.etLectureName);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
//        etDays = findViewById(R.id.etDays);
        btnAddLecture = findViewById(R.id.btnAddLecture);
        checkBoxes[0] = findViewById(R.id.sunday_checkbox);
        checkBoxes[1] = findViewById(R.id.monday_checkbox);
        checkBoxes[2] = findViewById(R.id.tuesday_checkbox);
        checkBoxes[3] = findViewById(R.id.wednesday_checkbox);
        checkBoxes[4] = findViewById(R.id.thursday_checkbox);
        checkBoxes[5] = findViewById(R.id.friday_checkbox);
        checkBoxes[6] = findViewById(R.id.saturday_checkbox);

        dbHelper = new DatabaseHelper(this);

        btnAddLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etLectureName.getText().toString().trim();
                String startTime = etStartTime.getText().toString().trim();
                String endTime = etEndTime.getText().toString().trim();
                String days="";
                String daysChecked = "";

                for (int i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].isChecked()) {
                        daysChecked += checkBoxes[i].getText().toString();
                    }
                }
                if (name.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || daysChecked.isEmpty() ) {
                    Toast.makeText(AddLecture.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
//                    Lecture lecture = new Lecture(0, name, startTime, endTime, days);
//                    dbHelper.insertLecture(lecture);
//                    Toast.makeText(AddLecture.this, "Lecture added successfully", Toast.LENGTH_SHORT).show();
                    addToDb(checkBoxes,name,startTime,endTime);
                    finish(); // Close the activity and return to the previous one
                }
            }
        });
    }

    public String addToDb(CheckBox[] checkBoxes,String name,String startTime,String endTime) {
        String days = "";
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                days += checkBoxes[i].getText().toString();
                Lecture lecture = new Lecture(0, name, startTime, endTime, days);
                dbHelper.insertLecture(lecture);
                days="";
            }
        }
        Toast.makeText(AddLecture.this, "Lecture added successfully", Toast.LENGTH_SHORT).show();
        return days;
    }
}
