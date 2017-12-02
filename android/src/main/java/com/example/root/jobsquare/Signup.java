package com.example.root.jobsquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity {
    EditText uname,uemail,upassword,ucontact,uage,ulocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uname = (EditText)findViewById(R.id.username);
        uemail = (EditText)findViewById(R.id.email);
        upassword = (EditText)findViewById(R.id.password);
        ucontact = (EditText)findViewById(R.id.contact);
        uage = (EditText)findViewById(R.id.age);
        ulocation = (EditText)findViewById(R.id.location);
    }
    public void onclick(View v){
        String name = uname.getText().toString();
        String email = uemail.getText().toString();
        String password = upassword.getText().toString();
        String contact = ucontact.getText().toString();
        String age = uage.getText().toString();
        String location = ulocation.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonrequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?username" + name +"&password=" + password, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                    Toast.makeText(Signup.this, "Registraion Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonrequest);
    }
}
