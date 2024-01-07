package com.example.applicationfull2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText messageEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEditText = findViewById(R.id.messageEditText);
    }
    public void goToSecondActivity(View view) {
        String message = messageEditText.getText().toString();

        // Store the message using the content provider
        storeMessage(message);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }
    public void startMyService(View view) {
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }

    private void storeMessage(String message) {
        ContentValues values = new ContentValues();
        values.put(MessageContract.MessageEntry.COLUMN_MESSAGE, message);
        Log.i("provider", message);
        getContentResolver().insert(MessageContract.MessageEntry.CONTENT_URI, values);
    }
}