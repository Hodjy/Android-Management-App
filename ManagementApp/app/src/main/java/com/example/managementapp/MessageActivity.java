package com.example.managementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MessageActivity extends AppCompatActivity {

    private EditText messageEditText;
    public Button sendTextButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageEditText = findViewById(R.id.message_to_send);
        sendTextButton = (Button)findViewById(R.id.send_text_btn);

        sendTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                //TODO Send message function

                /*
                ShareCompat.IntentBuilder intentBuilder = new ShareCompat.IntentBuilder;
                ShareCompat.IntentBuilder.from().setType("text/plain").setText(message)
                        .setChooserTitle("The message can be send using the following: ")
                        .startChooser();
                 */
            }
        });

    }





}
