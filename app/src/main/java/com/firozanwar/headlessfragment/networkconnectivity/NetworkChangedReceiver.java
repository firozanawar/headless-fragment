package com.firozanwar.headlessfragment.networkconnectivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class NetworkChangedReceiver extends BroadcastReceiver {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BUDDHA","NetworkChangedReceiver:");
//        Intent in = new Intent(NetworkHelper.CHECK_INTERNET);
//        in.putExtra(NetworkHelper.CHECK_INTERNET,
//                NetworkHelper.isInternetConnected(context));
//        LocalBroadcastManager.getInstance(context).sendBroadcast(in);
    }
}
