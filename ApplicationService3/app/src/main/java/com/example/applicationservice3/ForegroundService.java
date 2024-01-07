package com.example.applicationservice3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    private static final String CHANNEL_ID = "ForegroundServiceChannel";
    private int count = 0;
    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                updateNotification(count);
                handler.postDelayed(this, 1000); // Repeat every 1 second
            }
        };
        Log.i("oncreate", "created");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Notification notification = buildNotification("Foreground Service Example", "Counting: 0");
        startForeground(1, notification);
        handler.post(runnable);
        Log.i("onStartCommand", "start");
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        stopForeground(true);
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
        Log.i("notif created", "start");
    }
    private void updateNotification(int count) {
        Notification notification = buildNotification("Foreground Service Example", "Counting: " + count);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.notify(1, notification);
        Log.i("notif update", "start");
        Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show();
    }
    private Notification buildNotification(String title, String content) {
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();
    }
}