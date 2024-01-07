package com.example.applicationcontentprovider;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.net.Uri;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnAjouterNote;
    private Button btnRetrouverNotes;
    private Button btnDeleteStudent;
    private Button btnModifyStudent;
    private Button btnSearchStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAjouterNote = findViewById(R.id.imageButton);
        btnRetrouverNotes = findViewById(R.id.button);
        btnDeleteStudent = findViewById(R.id.btnDeleteStudent);
        btnModifyStudent = findViewById(R.id.btnModifyStudent);
        btnSearchStudent = findViewById(R.id.btnSearchStudent);
        // Set click listeners
        btnAjouterNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAjouterNoteInptiste(view);
            }
        });

        btnRetrouverNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRetrouverNotesInptistes(view);
            }
        });
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDeleteStudent(view);
            }
        });
        btnModifyStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickModifyStudent(view);
            }
        });
        btnSearchStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSearchStudent(view);
            }
        });
    }


    public void onClickAjouterNoteInptiste(View view) {

        ContentValues values = new ContentValues();
        values.put(INPTProvider.NOM, ((EditText) findViewById(R.id.nom)).getText().toString());
        values.put(INPTProvider.NUM, ((EditText) findViewById(R.id.num)).getText().toString());
        values.put(INPTProvider.EMAIL, ((EditText) findViewById(R.id.email)).getText().toString());
        values.put(INPTProvider.DATENAISS, ((EditText) findViewById(R.id.datenaiss)).getText().toString());
        Uri uri = getContentResolver().insert(INPTProvider.CONTENT_URI, values);
        assert uri != null;
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        Log.i("Insert", "Student inserted");
    }


    @SuppressLint("Range")
    public void onClickRetrouverNotesInptistes(View view) {
        // Retrouver les notes des etudiants
        String URL = "content://com.example.applicationcontentprovider/inptistes";
        Uri inptistes = Uri.parse(URL);
        Cursor c = getContentResolver().query(inptistes, null, null, null, "nom");

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Toast.makeText(this,
                            c.getString(c.getColumnIndex(INPTProvider._ID)) +
                                    "; " + c.getString(c.getColumnIndex(INPTProvider.NOM)) +
                                    "; " + c.getString(c.getColumnIndex(INPTProvider.NUM)) +
                                    "; " + c.getString(c.getColumnIndex(INPTProvider.EMAIL)) +
                                    "; " + c.getString(c.getColumnIndex(INPTProvider.DATENAISS)) ,
                            Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
                c.close(); // Close the cursor to release resources
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error retrieving data", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickDeleteStudent(View view) {
        String studentId = ((EditText) findViewById(R.id.editTextStudentId)).getText().toString();

        Uri uri = Uri.withAppendedPath(INPTProvider.CONTENT_URI, studentId);

        int deletedRows = getContentResolver().delete(uri, null, null);

        if (deletedRows > 0) {
            Toast.makeText(getBaseContext(), "Student deleted successfully", Toast.LENGTH_SHORT).show();
            Log.i("delete", "deleted student successfully");
        } else {
            Toast.makeText(getBaseContext(), "Failed to delete student", Toast.LENGTH_SHORT).show();
        }

    }
    public void onClickModifyStudent(View view) {
        String studentId = ((EditText) findViewById(R.id.editTextStudentId)).getText().toString();
        Uri uri = Uri.withAppendedPath(INPTProvider.CONTENT_URI, studentId);

        ContentValues updatedValues = new ContentValues();
        updatedValues.put(INPTProvider.NOM, "Updated Name");
        updatedValues.put(INPTProvider.NUM, "Updated Number");
        updatedValues.put(INPTProvider.EMAIL, "Updated Email");
        updatedValues.put(INPTProvider.DATENAISS, "Updated Date of Birth");

        int updatedRows = getContentResolver().update(uri, updatedValues, null, null);

        if (updatedRows > 0) {
            Toast.makeText(getBaseContext(), "Student updated successfully", Toast.LENGTH_SHORT).show();
            Log.i("Update successful", "Student updated successfully");
        } else {
            Toast.makeText(getBaseContext(), "Failed to update student", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("Range")
    public void onClickSearchStudent(View view) {
        String studentId = ((EditText) findViewById(R.id.editTextStudentId)).getText().toString();
        Uri uri = Uri.withAppendedPath(INPTProvider.CONTENT_URI, studentId);
        Cursor c = getContentResolver().query(uri, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(INPTProvider._ID)) +
                                "; " + c.getString(c.getColumnIndex(INPTProvider.NOM)) +
                                "; " + c.getString(c.getColumnIndex(INPTProvider.NUM)) +
                                "; " + c.getString(c.getColumnIndex(INPTProvider.EMAIL)) +
                                "; " + c.getString(c.getColumnIndex(INPTProvider.DATENAISS)) ,
                        Toast.LENGTH_SHORT).show();
                Log.i("Search", c.getString(c.getColumnIndex(INPTProvider._ID)) +
                        "; " + c.getString(c.getColumnIndex(INPTProvider.NOM)) +
                        "; " + c.getString(c.getColumnIndex(INPTProvider.NUM)) +
                        "; " + c.getString(c.getColumnIndex(INPTProvider.EMAIL)) +
                        "; " + c.getString(c.getColumnIndex(INPTProvider.DATENAISS)));
            } else {
                Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
            }

            c.close(); // Close the cursor to release resources
        } else {
            Toast.makeText(this, "Error retrieving student data", Toast.LENGTH_SHORT).show();
        }
    }

}