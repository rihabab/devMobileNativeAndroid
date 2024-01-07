package com.example.applicationservice3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {
    public BoundService() {
    }
    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public int add(int a, int b) {
        return a + b;
    }
}