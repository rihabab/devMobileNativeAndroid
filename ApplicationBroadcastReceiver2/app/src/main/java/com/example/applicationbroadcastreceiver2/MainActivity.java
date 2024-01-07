// MainActivity.java
package com.example.applicationbroadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MyReceiver monReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filtre = new IntentFilter();
        filtre.addAction("com.aseds.CUSTOM_INTENT");

        // Use the named receiver
        monReceiver = new MyReceiver();
        registerReceiver(monReceiver, filtre, Context.RECEIVER_EXPORTED);

        Button broadcastButton = findViewById(R.id.button);
        broadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trigger the broadcast when the button is clicked
                broadcastIntent();
                Log.i("button","clicked");
            }
        });
    }

    public void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction("com.aseds.CUSTOM_INTENT");
        sendBroadcast(intent);
        Log.i("broafdcastintent","received");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (monReceiver != null)
            unregisterReceiver(monReceiver);
        monReceiver = null;
    }
}
