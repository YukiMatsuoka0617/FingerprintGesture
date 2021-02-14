package com.example.fingerprintgesture;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.FingerprintGestureController;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.RequiresApi;

public class MyFingerprintGestureService extends AccessibilityService {
    public static FingerprintGestureController gestureController;
    private FingerprintGestureController
            .FingerprintGestureCallback fingerprintGestureCallback;
    private boolean mIsGestureDetectionAvailable = true;
    public MyFingerprintGestureService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onServiceConnected() {
        gestureController = getFingerprintGestureController();
        Log.e("test", "Is available: " + gestureController.isGestureDetectionAvailable() );
        Log.d("test","onServiceConnected:");
        if (fingerprintGestureCallback != null
                || !mIsGestureDetectionAvailable) {
            Log.d("test","onServiceConnected:");
            return;
        }

        fingerprintGestureCallback =
                new FingerprintGestureController.FingerprintGestureCallback() {
                    @Override
                    public void onGestureDetected(int gesture) {
                        Log.d("test","gesture:"+gesture);
                    }

                    @Override
                    public void onGestureDetectionAvailabilityChanged(boolean available) {
                        mIsGestureDetectionAvailable = available;
                        Log.d("test","mIsGestureDetectionAvailable:"+mIsGestureDetectionAvailable);
                    }
                };

        if (fingerprintGestureCallback != null) {
            gestureController.registerFingerprintGestureCallback(
                    fingerprintGestureCallback, null);
            Log.d("test","registerFingerprintGestureCallback:");
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.d("test","onAccessibilityEvent:");

    }

    @Override
    public void onInterrupt() {

    }


}
