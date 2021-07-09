package com.example.managementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity implements View.OnClickListener{

    TextView numberTv;
    ImageButton settings_btn;

    final int CALL_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // set this window to be LTR only
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);


        numberTv = findViewById(R.id.call_activity_phone_textView);
        settings_btn = findViewById(R.id.call_activity_settings_btn);

        Button btn1 = findViewById(R.id.call_activity_btn_1);
        Button btn2 = findViewById(R.id.call_activity_btn_2);
        Button btn3 = findViewById(R.id.call_activity_btn_3);
        Button btn4 = findViewById(R.id.call_activity_btn_4);
        Button btn5 = findViewById(R.id.call_activity_btn_5);
        Button btn6 = findViewById(R.id.call_activity_btn_6);
        Button btn7 = findViewById(R.id.call_activity_btn_7);
        Button btn8 = findViewById(R.id.call_activity_btn_8);
        Button btn9 = findViewById(R.id.call_activity_btn_9);

        Button btn0 = findViewById(R.id.call_activity_btn_0);
        Button btn_plus= findViewById(R.id.call_activity_btn_plus);
        Button btn_ladder = findViewById(R.id.call_activity_btn_ladder);
        Button btn_star = findViewById(R.id.call_activity_btn_star);

        ImageButton delete_btn = findViewById(R.id.call_activity_delete_btn);
        ImageButton call_btn = findViewById(R.id.call_activity_call_btn);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_ladder.setOnClickListener(this);
        btn_star.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
        call_btn.setOnClickListener(this);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String current = numberTv.getText().toString();
                if(current.length()>0) {
                    current = current.substring(0, current.length() - 1);
                    numberTv.setText(current);
                }
            }
        });

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hasCallPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);

                if(hasCallPermission == PackageManager.PERMISSION_GRANTED){
                    callPhone();
                }
                else{
                    requestPermissions(new String []{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST);
                }
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions,@Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CALL_PERMISSION_REQUEST)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                callPhone();
            }
            else{
                Toast.makeText(this, "Must give permission to call, please go to settings", Toast.LENGTH_SHORT).show();
                settings_btn.setVisibility(View.VISIBLE);
            }
        }
    }

    private void callPhone(){
        String phoneNumber = numberTv.getText().toString();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        numberTv.setText (numberTv.getText().toString() + ((Button)v).getText().toString());
    }
}