package com.example.root.jobsquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class jobcatagory extends AppCompatActivity {
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobcatagory);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
    }
    public void jobdetail (View v){
        Intent i = new Intent(getBaseContext(),jobdetails.class);
        i.putExtra("token",token);
        startActivity(i);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.xyz, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonrequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?",null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(jobcatagory.this, "job created", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getBaseContext(),previous_reqruitment.class);
                i.putExtra("previous",response.toString());
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(jobcatagory.this, "Error", Toast.LENGTH_SHORT).show();
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
        Intent i = new Intent(this, previous_reqruitment.class);
        startActivity(i);
        return true;
    }
}
