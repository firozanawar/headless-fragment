package com.firozanwar.headlessfragment.runtimepermission;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.firozanwar.headlessfragment.R;

public class RuntimePermissionActivity extends AppCompatActivity implements PermissionHelper.PermissionCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);


        PermissionHelper mPermissionHelper = (PermissionHelper) getSupportFragmentManager().findFragmentByTag(PermissionHelper.TAG);

        if (mPermissionHelper == null) {
            mPermissionHelper = PermissionHelper.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(mPermissionHelper, PermissionHelper.TAG)
                    .commit();
        }

        final PermissionHelper finalMPermissionHelper = mPermissionHelper;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finalMPermissionHelper.checkPermission();
            }
        }, 1000);
    }

    @Override
    public void onPermissionGranted() {
        Log.d("PermissionActivity", "onPermissionGranted");
    }

    @Override
    public void onPermissionDenied() {
        Log.d("PermissionActivity", "onPermissionDenied");
    }
}
