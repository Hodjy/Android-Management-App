package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextClock;

public class MainActivity extends AppCompatActivity {
    private LinearLayout sendMessageLayoutRef;
    private LinearLayout statisticLayoutRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessageLayoutRef = findViewById(R.id.sendMessageLayout);
        statisticLayoutRef = findViewById(R.id.phoneStatisticsLayout);


        sendMessageLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO HANDLE RETURN WITH TOAST
                Intent intent = new Intent(MainActivity.this , MessageActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        statisticLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , StatisticActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }


}