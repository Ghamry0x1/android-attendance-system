package com.example.student_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Sign;
    EditText StId;
    TextView SingUp;
    boolean PrefCheck = false;



    @Override
    protected void onResume() {
        super.onResume();
        Log.d("sdsd", "onResume: here");
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        String PrefStr = pref.getString(Constant.PrefID, null);
        if (PrefStr != null) {
            PrefCheck = true;
            StId.setText(PrefStr);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sign = findViewById(R.id.SignInBtn);
        StId = findViewById(R.id.Idtxt);
        SingUp = findViewById(R.id.signUp_text);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();



        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TempID = StId.getText().toString().trim();
                if (Constant.CheckID(TempID)){
                    // API call here
                    if (!PrefCheck) {
                        editor.putString(Constant.PrefID, TempID);
                        editor.apply();
                    }
                    Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                    myIntent.putExtra(Constant.IntentID, TempID);
                    MainActivity.this.startActivity(myIntent);
                }else{
                    Toast.makeText(MainActivity.this,getText(R.string.InvalidID),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
