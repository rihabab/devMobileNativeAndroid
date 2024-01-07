package com.example.applicationservice;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.provider.Settings;
public class UnService extends Service {
    private static final String TAG = "MonService";
    private MediaPlayer lecteur;
    public UnService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        Log.i(TAG, "Le service a ete cree");
        Toast.makeText(this, "Service cree", Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        lecteur = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);
        lecteur.setLooping(true);
        lecteur.start();
        Log.i(TAG,"Le service a ete demarre");
        Toast.makeText(this, "Service Demarre", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Le service a ete detruit");
        Toast.makeText(this, "Service Detruit", Toast.LENGTH_LONG).show();
        lecteur.stop();
    }
}