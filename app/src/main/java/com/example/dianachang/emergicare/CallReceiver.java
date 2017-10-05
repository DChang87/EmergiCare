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
            Log.i(TAG, "" + state);
            Log.i(TAG, "N" + incomingNumber);
            super.onCallStateChanged(state, incomingNumber);
            Log.i(TAG, "N" + incomingNumber);
            if(TelephonyManager.CALL_STATE_RINGING == state) {
                Log.i(TAG, "RINGING, number: " + incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
                Log.i(TAG, "OFFHOOK");
                Log.i(TAG, incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_IDLE == state) {
                Log.i(TAG, "IDLE");
            }
        }
    }
    public void start() {
        CallListener callListener = new CallListener();
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
