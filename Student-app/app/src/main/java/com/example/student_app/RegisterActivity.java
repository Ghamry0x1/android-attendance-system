package com.example.student_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button Sign;
    EditText StId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Sign = findViewById(R.id.SignInBtn);
        StId = findViewById(R.id.Idtxt);

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TempID = StId.getText().toString().trim();
                if (TempID.length() == 7) {     //  xxPxxxx
                    if (TempID.charAt(0) >= '0' && TempID.charAt(0) <= '9'){
                        if (TempID.charAt(1) >= '0' && TempID.charAt(1) <= '9'){
                            if (TempID.charAt(2) == 'P' || TempID.charAt(2) == 'p'){
                                if (TempID.charAt(3) >= '0' && TempID.charAt(3) <= '9'){
                                    if (TempID.charAt(4) >= '0' && TempID.charAt(4) <= '9'){
                                        if (TempID.charAt(5) >= '0' && TempID.charAt(5) <= '9'){
                                            if (TempID.charAt(6) >= '0' && TempID.charAt(6) <= '9'){
                                                Toast.makeText(RegisterActivity.this,getText(R.string.SignUpDone),Toast.LENGTH_SHORT).show();   //Change later
                                                onBackPressed(); //Change later
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,getText(R.string.InvalidID),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}