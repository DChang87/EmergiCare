package com.example.dianachang.emergicare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Date;

/**
 * Created by dianachang on 2016-09-17.
 */
public class CallReceiverReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("work", "AYY LMAO");
            // Extract phone number reformatted by previous receivers
            String phoneNumber = getResultData();
            if (phoneNumber == null) {
                // No reformatted number, use the original
                phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            }
        }
}
