package com.example.applicationfrag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the button by its ID
        Button loadFragmentButton = findViewById(R.id.loadFragmentButton);

        // Set a click listener for the button
        loadFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, load FragmentTwo
                loadFragment(new FragmentTwo());
                Log.i("onclick(): ", "fragment two");
            }
        });

        // Load the initial fragment (you can customize this)
        loadFragment(new FragmentOne());
        Log.i("oncreate(): ", "oncreate");
    }


    private void loadFragment(
            Fragment fragment) {
        // Remplace le contenu du conteneur avec le fragment spécifié
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // Ajoute la transaction à la pile de retour
        transaction.commit();
    }

}