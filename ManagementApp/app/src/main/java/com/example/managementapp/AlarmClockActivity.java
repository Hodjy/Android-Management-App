package com.example.managementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmClockActivity extends AppCompatActivity {
    //declared as global for ease of class use:
    private TimePicker m_TimePicker;
    private ToggleButton m_Day1Tb;
    private ToggleButton m_Day2Tb;
    private ToggleButton m_Day3Tb;
    private ToggleButton m_Day4Tb;
    private ToggleButton m_Day5Tb;
    private ToggleButton m_Day6Tb;
    private ToggleButton m_Day7Tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);

        Button saveBtn = findViewById(R.id.alarm_clock_save_btn);

        m_TimePicker = findViewById(R.id.alarm_clock_time_picker);
        m_Day1Tb = findViewById(R.id.alarm_clock_day1_btn);
        m_Day2Tb = findViewById(R.id.alarm_clock_day2_btn);
        m_Day3Tb = findViewById(R.id.alarm_clock_day3_btn);
        m_Day4Tb = findViewById(R.id.alarm_clock_day4_btn);
        m_Day5Tb = findViewById(R.id.alarm_clock_day5_btn);
        m_Day6Tb = findViewById(R.id.alarm_clock_day6_btn);
        m_Day7Tb = findViewById(R.id.alarm_clock_day7_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveAlarmClock(m_TimePicker, m_Day1Tb, m_Day2Tb, m_Day3Tb, m_Day4Tb, m_Day5Tb, m_Day6Tb, m_Day7Tb);
            }
        });
    }

    private void saveAlarmClock(TimePicker timePicker, ToggleButton day1Tb, ToggleButton day2Tb, ToggleButton day3Tb, ToggleButton day4Tb, ToggleButton day5Tb, ToggleButton day6Tb, ToggleButton day7Tb) {
        int hour, minutes;
        ArrayList<Integer> days;
        hour = timePicker.getHour();
        minutes = timePicker.getMinute();
        days = getDaysForAlarm(day1Tb, day2Tb, day3Tb, day4Tb,
                day5Tb, day6Tb, day7Tb);

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        intent.putExtra(AlarmClock.EXTRA_DAYS, days);

        startActivity(intent);
    }

    private ArrayList<Integer> getDaysForAlarm(ToggleButton day1, ToggleButton day2,
                                               ToggleButton day3, ToggleButton day4,
                                               ToggleButton day5, ToggleButton day6,
                                               ToggleButton day7)
    {
        ArrayList<Integer> days = new ArrayList<Integer>();

        days.add(day1.isChecked() ? Calendar.SUNDAY : 0);
        days.add(day2.isChecked() ? Calendar.MONDAY : 0);
        days.add(day3.isChecked() ? Calendar.TUESDAY : 0);
        days.add(day4.isChecked() ? Calendar.WEDNESDAY : 0);
        days.add(day5.isChecked() ? Calendar.THURSDAY : 0);
        days.add(day6.isChecked() ? Calendar.FRIDAY : 0);
        days.add(day7.isChecked() ? Calendar.SATURDAY : 0);

        return days;
    }
}