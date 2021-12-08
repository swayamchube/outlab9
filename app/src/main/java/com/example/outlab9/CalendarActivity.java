package com.example.outlab9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    private static String[] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = findViewById(R.id.calendarView);
        TextView selectedDate = findViewById(R.id.selectedDate);
        TextView studyText = findViewById(R.id.studyCount);
        TextView assignmentText = findViewById(R.id.assignmentCount);
        TextView examText = findViewById(R.id.examCount);
        TextView lectureText = findViewById(R.id.lectureCount);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                String message = MONTHS[month - 1] + " " + dayOfMonth + ", " + year;
                selectedDate.setText(message);

                String date = (dayOfMonth >= 10? dayOfMonth : "0" + dayOfMonth) + "-" + (month >= 10? month : "0" + month) + "-" + year;
                Log.d("DATE", date);

                DatabaseHelper dbHelper = new DatabaseHelper(CalendarActivity.this);
                ArrayList<EventModel> eventList = dbHelper.getWithTypeDate("study", date);
                int studyCount = (eventList == null? 0 : eventList.size());
                studyText.setText("Study Plans: " + studyCount);

                eventList = dbHelper.getWithTypeDate("assignment", date);
                int assignmentCount = (eventList == null? 0 : eventList.size());
                assignmentText.setText("Assignments: " + assignmentCount);

                eventList = dbHelper.getWithTypeDate("exam", date);
                int examCount = (eventList == null? 0 : eventList.size());
                examText.setText("Exams: " + examCount);

                eventList = dbHelper.getWithTypeDate("lecture", date);
                int lectureCount = (eventList == null? 0 : eventList.size());
                lectureText.setText("Lectures: " + lectureCount);
            }
        });

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}