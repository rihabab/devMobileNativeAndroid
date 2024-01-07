package com.example.applicationservice3;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class MyIntentService extends IntentService {
    private Handler handler;
    public MyIntentService() {
        super("MyIntentService");
        HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                performTask1();
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                performTask2();
            }
        });
    }
    private void performTask1() {
        for (int i = 0; i < 5; i++) {
            Log.d("MyIntentService", "Task 1 - Working... " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Log.d("MyIntentService", "Task 1 completed");
    }

    private void performTask2() {
        for (int i = 0; i < 3; i++) {
            Log.d("MyIntentService", "Task 2 - Working... " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Log.d("MyIntentService", "Task 2 completed");
    }
}