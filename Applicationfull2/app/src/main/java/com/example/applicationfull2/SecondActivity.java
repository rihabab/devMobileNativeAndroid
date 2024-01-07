package com.example.applicationfull2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView messageTextView = findViewById(R.id.messageTextView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("MESSAGE")) {
            String message = intent.getStringExtra("MESSAGE");
            messageTextView.setText(message);
        }
        Log.i("secondActivity", "started");
    }
}
