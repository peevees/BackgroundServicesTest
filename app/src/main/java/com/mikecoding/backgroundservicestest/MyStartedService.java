package com.mikecoding.backgroundservicestest;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyStartedService extends Service {

    private static final String TAG = MyStartedService.class.getSimpleName();

    @Override
    public void onCreate(){
        super.onCreate();

        Log.d(TAG, "onCreate: " + Thread.currentThread().getName());
    }
    public int onStartCommand(Intent intent, int flags, int startId){

        new MyAsyncTask();

        /*
        //works on main thread this will block main thread
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        Log.d(TAG, "onStartCommand: " + Thread.currentThread().getName());
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG, "onBind: " + Thread.currentThread().getName());
        return null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    class MyAsyncTask extends AsyncTask<Integer, String, Void> {

        private final String TAG = MyStartedService.class.getSimpleName();

        @Override
        protected void onPreExecute(){
            Log.d(TAG, "onPreExecute: " + Thread.currentThread().getName());
        }
        @Override
        protected Void doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: " + Thread.currentThread().getName());

            int i = 1;
            while (i <= 10){
                publishProgress("Counter is now " + String.valueOf(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate: " + values[0] + " " + Thread.currentThread().getName());
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            Log.d(TAG, "onPostExecute: " + Thread.currentThread().getName());
        }

    }


}
