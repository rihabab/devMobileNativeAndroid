package com.example.applicationcontentprovider;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
public class INPTProvider extends ContentProvider {
    public INPTProvider() {
    }
    static final String PROVIDER_NAME = "com.example.applicationcontentprovider";
    static final String URL = "content://" + PROVIDER_NAME + "/inptistes";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String _ID = "_id";
    static final String NOM = "nom";
    static final String NUM = "num";
    static final String EMAIL = "email";
    static final String DATENAISS = "jour";
    private static HashMap<String, String> INPTISTES_PROJECTION_MAP;
    static final int INPTISTES = 1;
    static final int INPTISTE_ID = 2;
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "inptistes", INPTISTES);
        uriMatcher.addURI(PROVIDER_NAME, "inptistes/#", INPTISTE_ID);
    }
    /**
     * declarations de constantes liees a la base de données
     */
    private SQLiteDatabase db;
    static final String DATABASE_NAME = "INPT";
    static final String INPTISTES_TABLE_NAME = "inptistes";
    static final int DATABASE_VERSION = 2;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + INPTISTES_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nom TEXT NOT NULL, " +
                    " num TEXT NOT NULL, " +
                    " email TEXT NOT NULL, " +
                    " jour TEXT NOT NULL); " ;
    /**
     * Helper class qui cree et gere le depot de données lie au provider
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + INPTISTES_TABLE_NAME);
            onCreate(db);
        }
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        /**
         * Créer une base de données
         */
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Insérer un inptiste
         */
        long rowID = db.insert(INPTISTES_TABLE_NAME, "", values);
        /**
         * Si l Etudiant a ete insere avec succes
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(INPTISTES_TABLE_NAME);
        // qb.setProjectionMap(INPTISTES_PROJECTION_MAP);
        switch (uriMatcher.match(uri)) {
            case INPTISTES:
                qb.setProjectionMap(INPTISTES_PROJECTION_MAP);
                break;
            case INPTISTE_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
        }
        if (sortOrder == null || sortOrder == ""){
            /**
             * Par defaut ordonner par nom d inptistes
             */
            sortOrder = NOM;
        }
        Cursor c = qb.query(db, projection, selection,
                selectionArgs,null, null, sortOrder);
        /**
         * souscrire pour ecouter un URI pour des changements
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case INPTISTES:
                count = db.delete(INPTISTES_TABLE_NAME, selection, selectionArgs);
                break;
            case INPTISTE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(INPTISTES_TABLE_NAME, _ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case INPTISTES:
                count = db.update(INPTISTES_TABLE_NAME, values, selection, selectionArgs);
                break;
            case INPTISTE_ID:
                count = db.update(INPTISTES_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Obtenir tous les inptistes
             */
            case INPTISTES:
                return "vnd.android.cursor.dir/vnd.example.inptistes";
            /**
             * Obtenir un inptiste particulier
             */
            case INPTISTE_ID:
                return "vnd.android.cursor.item/vnd.example.inptistes";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
} 