package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayContactActivity extends AppCompatActivity {

    private TextView m_NameTv;
    private TextView m_phoneNumberTv;
    private Button m_SaveBtn;

    private String m_Name;
    private String m_phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        m_NameTv = findViewById(R.id.display_contact_activity_fullNameTv);
        m_phoneNumberTv = findViewById(R.id.display_contact_activity_phoneNumberTv);
        m_SaveBtn = findViewById(R.id.display_contact_saveBtn);

        Bundle bundle = getIntent().getExtras();
        m_Name = bundle.getString("name");
        m_phoneNumber = bundle.getString("phone_number");

        m_NameTv.setText(m_Name);
        m_phoneNumberTv.setText(m_phoneNumber);

        m_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);

                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, m_Name);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, m_phoneNumber);

                startActivity(intent);
            }
        });
    }
}