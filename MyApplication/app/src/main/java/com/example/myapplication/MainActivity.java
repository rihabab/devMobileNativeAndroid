package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showAlertButton = findViewById(R.id.showAlertButton);
        showAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("Ceci est un message d'alerte personnalisé !");
            }
        });
    }
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Code à exécuter lorsque l'utilisateur clique sur OK
                        dialog.dismiss();
                    }
                });

        // Créer et afficher l'AlertDialog
        builder.create().show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Activity started by rihab", Toast.LENGTH_SHORT).show();
        Log.i("onStart():", "Activity started by rihab");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Activity restarted by rihab", Toast.LENGTH_SHORT).show();
        Log.i("onRestart():", "Activity restarted by rihab");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activity on resume by rihab", Toast.LENGTH_SHORT).show();
        Log.i("onResume():", "AActivity on resume by rihab");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Activity on pause by rihab", Toast.LENGTH_SHORT).show();
        Log.i("onPause():", "Activity on pause by rihab");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Activity stoped by rihab", Toast.LENGTH_SHORT).show();
        Log.i("onStop():", "Activity stoped by rihab");
    }

}