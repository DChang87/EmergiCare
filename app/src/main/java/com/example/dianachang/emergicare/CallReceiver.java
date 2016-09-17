package com.example.dianachang.emergicare;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReceiver extends Activity {


    private static final String TAG = "HelloService";

    private Context ctx;
    private TelephonyManager tm;

    private CallReceiverReceiver outgoingReceiver;

    public CallReceiver(Context ctx) {
        this.ctx = ctx;

        outgoingReceiver = new CallReceiverReceiver();
    }
    private class CallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
           // Log.i(TAG,"permission: "+ ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE));
            //Log.i(TAG,"permission: "+ ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE));
            Log.i(TAG, "" + state);
            Log.i(TAG, "N" + incomingNumber);
            super.onCallStateChanged(state, incomingNumber);
            Log.i(TAG, "N" + incomingNumber);
            if(TelephonyManager.CALL_STATE_RINGING == state) {
                Log.i(TAG, "RINGING, number: " + incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
                //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
                Log.i(TAG, "OFFHOOK");
                Log.i(TAG, incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_IDLE == state) {
                //when this state occurs, and your flag is set, restart your app
                Log.i(TAG, "IDLE");
            }
        }
    }
    public void start() {
        CallListener callListener = new CallListener();
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        TelephonyManager mTM = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * Stop calls detection.
     */
    public void stop() {
        ctx.unregisterReceiver(outgoingReceiver);
    }
}