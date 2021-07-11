package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView languageTv = findViewById(R.id.settings_languageTextView);
        TextView accessibilitySettingsTv = findViewById(R.id.settings_accessibilityTextView);
        TextView applicationTv = findViewById(R.id.settings_applicationTextView);
        TextView settingsTv = findViewById(R.id.settings_settingsTextView);


        applicationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
                startActivity(intent);
            }
        });

        languageTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }
        });


        accessibilitySettingsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });

        settingsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });

    }
}