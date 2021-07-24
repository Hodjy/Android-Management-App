package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class displayAlarmActivity extends AppCompatActivity {

    private TextView m_TimeTv;
    private TextView m_InDaysTv;
    private Button m_SaveBtn;

    private String m_Time;
    private String m_Days;
    private int m_Hour;
    private int m_Minutes;
    private ArrayList<Integer> m_DaysIntArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alarm);

        m_TimeTv = findViewById(R.id.display_alarm_timeTv);
        m_InDaysTv = findViewById(R.id.display_alarm_daysTv);
        m_SaveBtn = findViewById(R.id.display_alarm_saveBtn);

        Bundle bundle = getIntent().getExtras();
        m_Hour = bundle.getInt("hour");
        m_Minutes = bundle.getInt("minutes");
        m_DaysIntArray = bundle.getIntegerArrayList("days");

        m_Time = String.format(Locale.getDefault(),"%02d:%02d", m_Hour, m_Minutes);
        m_TimeTv.setText(m_Time);

        m_Days = extractDays(m_DaysIntArray);
        m_InDaysTv.setText(m_Days);


        m_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, m_Hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, m_Minutes);
                intent.putExtra(AlarmClock.EXTRA_DAYS, m_DaysIntArray);

                startActivity(intent);
            }
        });
    }

    private String extractDays(ArrayList<Integer> i_DaysIntArray) {

        StringBuilder daysBuilder = new StringBuilder();

        String[] weekdays = new DateFormatSymbols().getWeekdays();

        for(int dayNumber:i_DaysIntArray)
        {
            if(dayNumber !=0)
            {
                daysBuilder.append(weekdays[dayNumber]).append(",\n");
            }
        }

        if(daysBuilder.length() == 0)
        {
            daysBuilder.append(getResources().getString(R.string.display_alarm_activity_didntchooseDays));
        }
        else
        {
            daysBuilder.delete(daysBuilder.length() - 2, daysBuilder.length()-1);
        }

        return daysBuilder.toString();
    }

}