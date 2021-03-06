package com.example.mobile_client;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button Sign;
    EditText StId;
    EditText StName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final RequestQueue queue = Volley.newRequestQueue(this);
        Sign = findViewById(R.id.SignInBtn);
        StId = findViewById(R.id.Idtxt);
        StName = findViewById(R.id.Nametxt);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String TempID = StId.getText().toString().trim();
                final String TempName = StName.getText().toString().trim();
                if (Constant.CheckName(TempName)){
                    if (Constant.CheckID(TempID)) {
                        try {
                            JSONObject user = new JSONObject();
                            JSONObject reader = new JSONObject();
                            user.put("name", TempName);
                            user.put("id", TempID);
                            reader.put("student", user);
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                    (Request.Method.POST, Constant.RegisterURL, reader, new Response.Listener<JSONObject>() {

                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast.makeText(getApplicationContext(), getText(R.string.SignUpSuccess), Toast.LENGTH_SHORT).show();
                                            editor.putString(Constant.PrefID, TempID);
                                            editor.commit();
                                            onBackPressed();
                                        }
                                    }, new Response.ErrorListener() {

                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("tt", "code: " + error.networkResponse.statusCode);
                                            if (error.networkResponse.statusCode == 400) {
                                                Toast.makeText(getApplicationContext(), getText(R.string.SignUpDone), Toast.LENGTH_SHORT).show();
                                            } else {
                                                error.printStackTrace();
                                                Toast.makeText(getApplicationContext(), getText(R.string.error), Toast.LENGTH_SHORT).show();
                                            }
                                            onBackPressed();
                                        }
                                    });

                            queue.add(jsonObjectRequest);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, getText(R.string.InvalidID), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, getText(R.string.InvalidName), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}