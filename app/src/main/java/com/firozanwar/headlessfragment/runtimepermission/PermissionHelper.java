package com.firozanwar.headlessfragment.runtimepermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PermissionHelper extends Fragment {

    private static final int REQUEST_CAMERA_MIC_PERMISSIONS = 10;
    public static final String TAG = "CamMicPerm";

    private PermissionCallback mCallback;
    private static boolean sPermissionDenied;
    private Activity mActivity;
    private Context mContext;

    public static PermissionHelper newInstance() {
        return new PermissionHelper();
    }

    public PermissionHelper() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        this.mActivity = activity;

        if(activity instanceof PermissionCallback)
            mCallback = (PermissionCallback) activity;
        else {
            throw new IllegalArgumentException("activity must extend BaseActivity and implement LocationHelper.LocationCallback");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void checkPermission(){
        if(PermissionUtil.hasSelfPermission(getActivity(),new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO})){
            mCallback.onPermissionGranted();
        }else{
            if (!sPermissionDenied) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, REQUEST_CAMERA_MIC_PERMISSIONS);
            }
        }
    }

    public void setPermissionDenied(boolean cameraMicPermissionDenied) {
        this.sPermissionDenied = cameraMicPermissionDenied;
    }

    public static boolean isPermissionDenied() {
        return sPermissionDenied;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_MIC_PERMISSIONS) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                mCallback.onPermissionGranted();
            } else {
                Log.i("BaseActivity", "LOCATION permission was NOT granted.");
                mCallback.onPermissionDenied();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }


    public interface PermissionCallback {
        void onPermissionGranted();
        void onPermissionDenied();
    }
}
