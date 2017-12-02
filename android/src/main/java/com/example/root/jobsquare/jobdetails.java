package com.example.root.jobsquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class jobdetails extends AppCompatActivity {
    String token;
    EditText jobtype,location,mwages,rtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetails);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");

        jobtype = (EditText)findViewById(R.id.jobtype);
        jobtype.setText("fdfdfasf");

        location = (EditText)findViewById(R.id.location);
        mwages = (EditText)findViewById(R.id.wage);
        rtime = (EditText)findViewById(R.id.required);
    }
    public void oncreate(View v){
        String jtype = jobtype.getText().toString();
        String locat = location.getText().toString();
        String wage = mwages.getText().toString();
        String date = rtime.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonrequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?",null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    Toast.makeText(jobdetails.this, "job created", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(),hirefeed.class);
                    i.putExtra("job_avail_guys",response.toString());
                    i.putExtra("token",token);
                    startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(jobdetails.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);
                return params;
            }
        };
        queue.add(jsonrequest);
    }

}
