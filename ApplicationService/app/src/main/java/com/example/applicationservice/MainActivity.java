package com.example.applicationservice;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Start the service
    public void demarrerService(View view) {
        startService(new Intent(this, UnService.class));
        Log.i("StartService():", "Activite est creee");
    }
    // Stop the service
    public void arreterService(View view) {
        stopService(new Intent(this, UnService.class));
    }
}