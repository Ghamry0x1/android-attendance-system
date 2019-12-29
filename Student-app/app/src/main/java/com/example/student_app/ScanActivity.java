package com.example.student_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

public class ScanActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;


    public void KillApp(){
        Toast.makeText(ScanActivity.this,getString(R.string.IdiotUser),Toast.LENGTH_SHORT).show();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        final RequestQueue queue = Volley.newRequestQueue(this);

        final String url = "http://104.154.52.199:3000/attendance";

        if (ContextCompat.checkSelfPermission(ScanActivity.this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted

            //ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
            //Check again
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ScanActivity.this,Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},Constant.CAMERA_PERMISSION_CODE);
            }else{
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ScanActivity.this,new String[]{Manifest.permission.CAMERA},Constant.CAMERA_PERMISSION_CODE);
            }
        } else {/* Permission has already been granted*/}

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String myRes = result.getText();
                        try {
                            JSONObject reader = new JSONObject(myRes);
                            JSONObject user = new JSONObject();
                            user.put("id", getIntent().getStringExtra(Constant.IntentID));
                            reader.put("student", user);
                            Log.d("txt", reader.toString()+"");
                            Log.d("txt", "run: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                    (Request.Method.POST, url, reader, new Response.Listener<JSONObject>() {

                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast.makeText(getApplicationContext(), getText(R.string.Attend), Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }
                                    }, new Response.ErrorListener() {

                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("tt", "code: " + error.networkResponse.statusCode);
                                            if (error.networkResponse.statusCode == 400) {
                                                Toast.makeText(getApplicationContext(), getText(R.string.AttendDone), Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getApplicationContext(), getText(R.string.error), Toast.LENGTH_SHORT).show();
                                            }
                                            onBackPressed();
                                        }
                                    });

                            queue.add(jsonObjectRequest);
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
                if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    mCodeScanner.startPreview();
                }else{ KillApp(); }
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
