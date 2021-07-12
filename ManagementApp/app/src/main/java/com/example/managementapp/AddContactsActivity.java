package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        EditText nameET = findViewById(R.id.addContacts_nameET);
        EditText lastNameET = findViewById(R.id.addContacts_lastNameET);
        EditText phoneNumberET = findViewById(R.id.addContacts_phoneNumberET);
        Button saveBtn = findViewById(R.id.addContacts_saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);

                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME,
                        nameET.getText() + " " + lastNameET.getText());
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumberET.getText());

                startActivity(intent);
            }
        });
    }
}