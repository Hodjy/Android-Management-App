package com.example.managementapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private EditText messageEditText;
    public Button sendTextButton;

    final int SPEECH_RECOGNITION_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageEditText = findViewById(R.id.message_to_send);
        sendTextButton = (Button) findViewById(R.id.send_text_btn);
        ImageView speechBtn = findViewById(R.id.speech_btn);

        sendTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();

                if(!message.isEmpty())
                {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.setType("text/plain");
                    startActivity(intent);
                }
                else
                {
                    String emptyMsg =  getResources().getString(R.string.message_activity_emptyMsg);
                    Toast.makeText(MessageActivity.this, emptyMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        speechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String howToSpeakMsg = getResources().getString(R.string.message_activity_howToSpeak);

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, howToSpeakMsg);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "iw");
                startActivityForResult(intent, SPEECH_RECOGNITION_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == SPEECH_RECOGNITION_REQUEST && resultCode == RESULT_OK){

                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                messageEditText.setText(results.get(0));
            }
        }
}
