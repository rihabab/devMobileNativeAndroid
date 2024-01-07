package com.example.applicationfull;

import android.net.Uri;
import android.provider.BaseColumns;

public class MessageContract {

    private MessageContract() {
        // Private constructor to prevent instantiation
    }

    public static class MessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_MESSAGE = "message";

        // Content URI for the content provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                MyContentProvider.CONTENT_URI, TABLE_NAME
        );
    }
}

