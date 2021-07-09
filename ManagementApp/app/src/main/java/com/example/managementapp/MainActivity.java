package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout sendMessageLayoutRef = findViewById(R.id.sendMessageLayout);
        LinearLayout statisticLayoutRef = findViewById(R.id.phoneStatisticsLayout);
        LinearLayout calendarLayoutRef = findViewById(R.id.calendarLayout);
        LinearLayout callLayoutRef = findViewById(R.id.callLayout);




        sendMessageLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , MessageActivity.class);
                startActivity(intent);
            }
        });

        statisticLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , StatisticActivity.class);
                startActivity(intent);
            }
        });


        calendarLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        callLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                startActivity(intent);
            }
        });
    }
}