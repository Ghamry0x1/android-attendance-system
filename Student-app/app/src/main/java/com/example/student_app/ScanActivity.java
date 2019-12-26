package com.example.student_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

public class ScanActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    final int CAMERA_PERMISSION_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);


        if (ContextCompat.checkSelfPermission(ScanActivity.this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ScanActivity.this,Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.d("cool", "here");
                Toast.makeText(ScanActivity.this,"why are you like this?",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
                Log.d("cool", "No here");
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted

        }
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String myRes = result.getText();
                        Log.d("ete", "run: " + myRes);
                        try {
                            JSONObject reader = new JSONObject(myRes);
                            JSONObject user = new JSONObject();
                            user.put("id", getIntent().getStringExtra("key"));
                            reader.put("student", user);
                            Log.d("ete", "run: myRes" + reader.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }
}
