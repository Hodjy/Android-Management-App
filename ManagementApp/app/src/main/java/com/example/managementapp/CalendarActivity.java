package com.example.managementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private String m_DateFormat;
    private String m_TimeFormat;
    private SimpleDateFormat m_Sdf;
    private final Calendar m_InstancedCalendar = Calendar.getInstance();
    private String m_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        m_DateFormat = getResources().getString(R.string.dateFormatting);
        m_TimeFormat = getResources().getString(R.string.timeFormatting);
        m_Sdf = new SimpleDateFormat(m_DateFormat, Locale.getDefault());

        CalendarView calendarCV = findViewById(R.id.calendar_inputCalendar);
        TimePicker timePicker = findViewById(R.id.calendar_timepickerStartTime);
        EditText eventTitleET = findViewById(R.id.calendar_eventTitleET);
        EditText eventContentsET = findViewById(R.id.calendar_eventContentsET);
        Button saveBtn = findViewById(R.id.calendar_saveBtn);

        m_Date =  m_Sdf.format(new Date(calendarCV.getDate()));

        calendarCV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                m_InstancedCalendar.set(year,month,dayOfMonth);
                m_Date = m_Sdf.format(m_InstancedCalendar.getTime());
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String eventTitleText = eventTitleET.getText().toString();
                String eventContentText = eventContentsET.getText().toString();
                long millis = getUserSelectedTimeInMillis(timePicker, calendarCV);

                if(!eventTitleText.isEmpty() && !eventContentText.isEmpty())
                {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE , eventTitleText);
                    intent.putExtra(CalendarContract.Events.DESCRIPTION , eventContentText);
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "");
                    intent.putExtra(CalendarContract.Events.ALL_DAY, false);
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, millis);

                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(CalendarActivity.this, "Please fill the required forms.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private long getUserSelectedTimeInMillis(TimePicker timePicker, CalendarView calendar) {
        String time = String.format(Locale.getDefault(),"%02d:%02d", timePicker.getHour() , timePicker.getMinute());
        String dateToConvert = m_Date + " " + time;
        LocalDateTime ldt = LocalDateTime.parse(dateToConvert, DateTimeFormatter.ofPattern(m_DateFormat + " " + m_TimeFormat));
        long millis = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return millis;
    }
}