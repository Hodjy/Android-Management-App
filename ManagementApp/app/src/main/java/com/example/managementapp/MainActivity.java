package com.example.managementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final int READ_PERMISSION_REQUEST = 1;
    List<UserEvent> userEventList;

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



        //RecyclerView//

        userEventList = new ArrayList<>();

        RecyclerView eventsRecyclerView = findViewById(R.id.main_activity_eventsRecyclerView);
        eventsRecyclerView.setHasFixedSize(true);

        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        //TODO get the userEvents data and put it on this list

        int hasCallPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR);

        if(hasCallPermission == PackageManager.PERMISSION_GRANTED){
            userEventList = extractUserEventsFromCalendar(this);
        }
        else{
            requestPermissions(new String []{Manifest.permission.READ_CALENDAR}, READ_PERMISSION_REQUEST);
        }


        UserEventAdapter userEventAdapter = new UserEventAdapter(userEventList);
        eventsRecyclerView.setAdapter(userEventAdapter);
        //RecyclerView//

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

    public List<UserEvent> extractUserEventsFromCalendar(Context context) {

        ContentResolver contentResolver = context.getContentResolver();
        List<UserEvent> userEvents= new ArrayList<>();

        //selection build
        long now = new Date().getTime();
        String nowTime = String.valueOf(now);
        String threeYearsFromNow = String.valueOf(now + (DateUtils.DAY_IN_MILLIS * 1095));
        String selection = "((" + CalendarContract.Events.DTSTART + ">" + nowTime +") AND ("
                +CalendarContract.Events.DTEND + "<" + threeYearsFromNow + "))";

        Cursor cursor = contentResolver.query(CalendarContract.Events.CONTENT_URI,new String[]{
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND},selection, null, CalendarContract.Events.DTSTART + " ASC");

        if(cursor.moveToFirst())
        {
            String dateFormat = getResources().getString(R.string.dateFormatting);
            String timeFormat = getResources().getString(R.string.timeFormatting);
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat + "    " + timeFormat, Locale.getDefault());
            Date date;
            do{
                String title = cursor.getString(0);
                String description =cursor.getString(1);
                date = new Date(cursor.getLong(2));
                String dstart = sdf.format(date);

                userEvents.add(new UserEvent(title, description, dstart));

            }while (cursor.moveToNext());
        }


        return userEvents;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions, @Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == READ_PERMISSION_REQUEST)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                userEventList = extractUserEventsFromCalendar(MainActivity.this);
            }
            else{
                requestPermissions(new String []{Manifest.permission.READ_CALENDAR}, READ_PERMISSION_REQUEST);
            }
        }
    }



}