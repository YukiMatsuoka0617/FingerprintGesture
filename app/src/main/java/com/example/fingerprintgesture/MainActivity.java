package com.example.fingerprintgesture;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.FingerprintGestureController;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test","onCreate:Main");

        FingerprintManager fingerprintManager = (FingerprintManager)this.getSystemService(FingerprintManager.class);
        boolean flag = fingerprintManager.isHardwareDetected();
        Log.d("test","isHardwareDetected:"+ flag);

        Intent intent = new Intent(this, MyFingerprintGestureService.class);
        startService(intent);

    }
}