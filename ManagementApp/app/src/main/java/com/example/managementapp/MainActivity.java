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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessageLayoutRef = findViewById(R.id.sendMessageLayout);

        sendMessageLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO HANDLE RETURN WITH TOAST & check about clickable layout 
                Intent intent = new Intent(v.getContext(), MessageActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }


}