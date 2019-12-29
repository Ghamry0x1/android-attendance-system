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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
        final RequestQueue queue = Volley.newRequestQueue(this);

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
                final String TempID = StId.getText().toString().trim();
                Log.d("txttttttttttt", TempID+"");
                if (Constant.CheckID(TempID)){
                    try {
                        JSONObject user = new JSONObject();
                        JSONObject reader = new JSONObject();
                        user.put("id", TempID);
                        reader.put("student", user);
                        Log.d("txt", reader.toString()+"");
                        Log.d("txt", "run: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, Constant.LoginURL, reader, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getApplicationContext(), getText(R.string.LoginSuccess), Toast.LENGTH_SHORT).show();
                                        Log.d("asdas", "onResponse: "+ response.toString());
                                        editor.putString(Constant.PrefID, TempID);
                                        editor.apply();
                                        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                                        myIntent.putExtra(Constant.IntentID, TempID);
                                        MainActivity.this.startActivity(myIntent);
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("tt", "code: " + error.networkResponse.statusCode);
                                        if (error.networkResponse.statusCode == 400) {
                                            Toast.makeText(getApplicationContext(), getText(R.string.NoLogin), Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), getText(R.string.error), Toast.LENGTH_SHORT).show();
                                            error.printStackTrace();
                                        }
                                    }
                                });

                        queue.add(jsonObjectRequest);
                    } catch (JSONException e) {
                        Log.d("tt", "Hereeeee");
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(MainActivity.this,getText(R.string.InvalidID),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
