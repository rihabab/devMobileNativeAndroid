package com.example.applicationfull2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

    private static final String TAG = "MyContentProvider";

    // URI for the content provider
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.applicationfull2");

    @Override
    public boolean onCreate() {
        // Initialization logic here
        Log.d(TAG, "Content Provider created");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Query logic here
        return null;
    }

    @Override
    public String getType(Uri uri) {
        // Return the MIME type
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Insert logic here
        Log.d(TAG, "Inserting message: " + values.getAsString(MessageContract.MessageEntry.COLUMN_MESSAGE));
        Log.i("inserting",values.getAsString(MessageContract.MessageEntry.COLUMN_MESSAGE));
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Delete logic here
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Update logic here
        return 0;
    }
}
