package com.example.managementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(nameET.getText().toString().equals("") || phoneNumberET.getText().toString().equals(""))
                {
                    Toast.makeText(AddContactsActivity.this,
                            getResources().getString(R.string.addContacts_activity_pleaseInsert),
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(AddContactsActivity.this, displayContactActivity.class);

                    intent.putExtra("name", nameET.getText().toString() + " " + lastNameET.getText().toString());
                    intent.putExtra("phone_number", phoneNumberET.getText().toString());

                    startActivity(intent);
                }
            }
        });
    }
}