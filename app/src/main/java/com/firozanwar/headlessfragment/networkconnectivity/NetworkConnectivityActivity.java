package com.firozanwar.headlessfragment.networkconnectivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.headlessfragment.R;

public class NetworkConnectivityActivity extends AppCompatActivity {

    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNetworkReceiver = new NetworkChangedReceiver();

        // Use headless Fragment to check network connectivity, Uncomment below code.
        //registerNetworkBroadcastForNougat();

        NetworkHelper networkHelper = (NetworkHelper) getSupportFragmentManager().findFragmentByTag(NetworkHelper.TAG);

        if (networkHelper == null) {
            networkHelper = NetworkHelper.getInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(networkHelper, NetworkHelper.TAG)
                    .commit();
        }
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterNetworkChanges();
    }
}
