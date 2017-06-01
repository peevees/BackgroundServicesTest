package com.mikecoding.backgroundservicestest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();
    public MyIntentService() {
        super("MyWorkerThread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: " + Thread.currentThread().getName());
    }
    @Override
    protected void onHandleIntent(Intent intent){
        Log.d(TAG, "onHandleIntent: " + Thread.currentThread().getName());

        int i = 1;
        while (i <= 10){
            Log.d("Counter is now: ",  "" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + Thread.currentThread().getName());
    }
}
