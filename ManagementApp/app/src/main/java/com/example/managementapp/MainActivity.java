package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout sendMessageLayoutRef = findViewById(R.id.sendMessageLayout);
        LinearLayout calendarLayoutRef = findViewById(R.id.calendarLayout);
        LinearLayout callLayoutRef = findViewById(R.id.callLayout);
        LinearLayout gpsLayoutRef = findViewById(R.id.gpsLayout);
        LinearLayout alarmClockLayoutRef = findViewById(R.id.alarmClockLayout);
        LinearLayout settingsLayoutRef = findViewById(R.id.settingsLayout);
        LinearLayout addContactsLayoutRef = findViewById(R.id.addContactLayout);

        RecyclerView eventsRecyclerView = findViewById(R.id.main_activity_eventsRecyclerView);
        eventsRecyclerView.setHasFixedSize(true);

        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        sendMessageLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , MessageActivity.class);
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

        gpsLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );

                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"));
                    startActivity(intent);
                }
                else{
                    String message = getResources().getString(R.string.main_activity_turnOnGps);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        alarmClockLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmClockActivity.class);
                startActivity(intent);
            }
        });

        settingsLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        addContactsLayoutRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactsActivity.class);
                startActivity(intent);
            }
        });

    }
}