package com.example.student_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    Button ToCam;
    ImageView Logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Logo = findViewById(R.id.imageView3);
        ToCam = findViewById(R.id.button);

        Logo.setImageResource(R.drawable.logo);
        ToCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, ScanActivity.class);
                myIntent.putExtra(Constant.IntentID, getIntent().getStringExtra(Constant.IntentID));
                Main2Activity.this.startActivity(myIntent);
            }
        });
    }
}
