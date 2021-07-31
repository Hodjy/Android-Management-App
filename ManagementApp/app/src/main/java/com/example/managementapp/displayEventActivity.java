package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayEventActivity extends AppCompatActivity {

    private TextView m_TitleTv;
    private TextView m_DescriptionTv;
    private TextView m_DateTv;
    private TextView m_TimeTv;
    private Button m_SaveBtn;

    private String m_Title;
    private String m_Description;
    private String m_Date;
    private String m_Time;
    private Long m_EventBeginTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);

        m_TitleTv = findViewById(R.id.display_event_titleTv);
        m_DescriptionTv = findViewById(R.id.display_event_descriptionTv);
        m_DateTv = findViewById(R.id.display_event_dateTv);
        m_TimeTv = findViewById(R.id.display_event_timeTv);
        m_SaveBtn = findViewById(R.id.display_event_saveBtn);

        Bundle bundle = getIntent().getExtras();
        m_Title = bundle.getString("title");
        m_Description = bundle.getString("description");
        m_Date = bundle.getString("date");
        m_Time = bundle.getString("time");
        m_EventBeginTime = bundle.getLong("event_begin_time");

        m_TitleTv.setText(m_Title);
        m_DescriptionTv.setText(m_Description);
        m_DateTv.setText(m_Date);
        m_TimeTv.setText(m_Time);


        m_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE , m_Title);
                intent.putExtra(CalendarContract.Events.DESCRIPTION , m_Description);
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "");
                intent.putExtra(CalendarContract.Events.ALL_DAY, false);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, m_EventBeginTime);

                startActivity(intent);
            }
        });

    }
}