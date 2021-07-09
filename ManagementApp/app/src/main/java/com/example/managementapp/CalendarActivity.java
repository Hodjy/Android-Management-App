package com.example.managementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    private String m_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendar = findViewById(R.id.calendar_inputCalendar);
        TimePicker timePicker = findViewById(R.id.calendar_timepicker);
        EditText eventTitleET = findViewById(R.id.calendar_eventTitleET);
        EditText eventContentsET = findViewById(R.id.calendar_eventContentsET);
        Button saveBtn = findViewById(R.id.calendar_saveBtn);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                m_date = year + "/" + month + "/" + dayOfMonth;
                Toast.makeText(CalendarActivity.this, m_date, Toast.LENGTH_LONG).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String eventTitleText = eventTitleET.getText().toString();
                String eventContentText = eventContentsET.getText().toString();
                String time = timePicker.getHour() + ":" + timePicker.getMinute() + ":00";

                m_date = m_date + " " + time;
                LocalDateTime ldt = LocalDateTime.parse(m_date, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                long millis = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

                if(!eventTitleText.isEmpty() && !eventContentText.isEmpty() && !m_date.isEmpty())
                {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    if(true) //If you have the app.
                    {
                        intent.setData(CalendarContract.Events.CONTENT_URI);
                        intent.putExtra(CalendarContract.Events.TITLE , eventTitleText);
                        intent.putExtra(CalendarContract.Events.DESCRIPTION , eventContentText);
                        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "");
                        intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, millis);

                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(CalendarActivity.this, "Theres no app that can support this action.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(CalendarActivity.this, "Please fill the required forms.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}