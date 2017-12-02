package com.example.root.jobsquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {
    EditText uname,upassword;
    String token = "cvcvc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onclick(View v){
        if(v.getId() == R.id.mlogin){

            uname = (EditText)findViewById(R.id.username);
            String username = uname.getText().toString();
            upassword = (EditText)findViewById(R.id.password);
            String password = upassword.getText().toString();

            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?username" + username +"&password=" + password, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(MainActivity.this, "Welcome to jobSquare", Toast.LENGTH_SHORT).show();
                        String token = response.getString("token");
                        Intent i = new Intent(MainActivity.this,Home.class);
                        i.putExtra("token",token);
                        startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getBaseContext(), "failure", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonrequest);
            Intent i = new Intent(getBaseContext(),Home.class);
            i.putExtra("token",token);
            startActivity(i);
        }
        if (v.getId() == R.id.register){
            Intent i = new Intent(getBaseContext(),Signup.class);
            startActivity(i);
        }

    }
}
