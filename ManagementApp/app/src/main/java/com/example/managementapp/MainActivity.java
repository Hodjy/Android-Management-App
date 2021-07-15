package com.example.managementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    
    List<UserEvent> m_UserEventList;
    UserEventAdapter m_UserEventAdapter;
    RecyclerView m_EventsRecyclerView;

    final int READ_PERMISSION_REQUEST = 1;

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

        m_UserEventList = new ArrayList<>();
        m_EventsRecyclerView = findViewById(R.id.main_activity_eventsRecyclerView);
        m_EventsRecyclerView.setHasFixedSize(true);
        m_EventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        int hasCallPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR);

        if(hasCallPermission == PackageManager.PERMISSION_GRANTED){
            initializeEventsRecyclerView();
        }
        else{
            requestPermissions(new String []{Manifest.permission.READ_CALENDAR}, READ_PERMISSION_REQUEST);
        }

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

    private List<UserEvent> extractUserEventsFromCalendar(Context context) {

        ContentResolver contentResolver = context.getContentResolver();
        List<UserEvent> userEvents= new ArrayList<>();

        //selection build
        long now = new Date().getTime();
        String nowTime = String.valueOf(now);
        String threeYearsFromNow = String.valueOf(now + (DateUtils.DAY_IN_MILLIS * 1095));
        String selection = "((" + CalendarContract.Events.DTSTART + ">" + nowTime +") AND ("
                +CalendarContract.Events.DTEND + "<" + threeYearsFromNow + "))";

        //extract all the events to cursor
        Cursor cursor = contentResolver.query(CalendarContract.Events.CONTENT_URI,new String[]{
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND},selection, null, CalendarContract.Events.DTSTART + " ASC");

        //extract the events to user event list
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

    private void initializeEventsRecyclerView(){

        m_UserEventList = extractUserEventsFromCalendar(MainActivity.this);
        m_UserEventAdapter = new UserEventAdapter(m_UserEventList);
        m_EventsRecyclerView.setAdapter(m_UserEventAdapter);
        m_UserEventAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions, @Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == READ_PERMISSION_REQUEST)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                initializeEventsRecyclerView();
            }
            else{
                requestPermissions(new String []{Manifest.permission.READ_CALENDAR}, READ_PERMISSION_REQUEST);
            }
        }
    }
}