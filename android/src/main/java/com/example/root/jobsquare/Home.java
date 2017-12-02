package com.example.root.jobsquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Home extends AppCompatActivity {
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
    }
    public void onclick(View v){
        if(v.getId() == R.id.hire){
            Intent i = new Intent(getBaseContext(),jobcatagory.class);
            i.putExtra("token",token);
            startActivity(i);
        }
        if(v.getId() == R.id.work){
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonrequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?",null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Intent i = new Intent(getBaseContext(),jobfeed.class);
                    i.putExtra("job_avail",response.toString());
                    i.putExtra("token",token);
                    startActivity(i);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
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
}
