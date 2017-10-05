package com.example.dianachang.emergicare;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "HelloService";
    private CallReceiver callReceiver;
    private boolean isRunning  = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service onStartCommand");
        Toast.makeText(this, "Start Command", Toast.LENGTH_LONG).show();

        callReceiver = new CallReceiver(this);

        int res = super.onStartCommand(intent,flags, startId);
        callReceiver.start();
        return res;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        isRunning = false;

        Log.i(TAG, "Service onDestroy");
    }

}
