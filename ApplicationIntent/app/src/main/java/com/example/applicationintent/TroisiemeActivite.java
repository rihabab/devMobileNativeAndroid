package com.example.applicationintent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TroisiemeActivite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troisieme);

        // Récupérez les données de l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nom = extras.getString("nom");
            String prenom = extras.getString("prenom");

            // Affichez les données dans un TextView (à ajouter dans le layout correspondant)
            TextView textView = findViewById(R.id.textView);
            textView.setText("Nom : " + nom + "\nPrénom : " + prenom);
        }
    }
}
