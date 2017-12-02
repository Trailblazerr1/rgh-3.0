package com.example.root.jobsquare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class jobfeed extends AppCompatActivity {
    String token;
    JSONArray js;
    ArrayList <info> infolist;
    work_adapter wa;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobfeed);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        lv = (ListView)findViewById(R.id.list1);
        try {
            js  = new JSONArray(getIntent().getStringExtra("job_avail"));
            for(int i =0;i<js.length();i++){
                JSONObject x = js.getJSONObject(i);

                String name;
                String wage;
                String date;
                String location;
                int id;
                name = x.getString("name");
                wage = x.getString("wage");
                date = x.getString("date");
                location = x.getString("location");
                id = x.getInt("id");

                info in = new info();
                in.setDate(date);
                in.setName(name);
                in.setWage(wage);
                in.setLocation(location);
                in.setId(id);
                infolist.add(in);
            }
            wa = new work_adapter(jobfeed.this,infolist);
            lv.setAdapter(wa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int id = infolist.get(i).getId();
                    RequestQueue queue = Volley.newRequestQueue(jobfeed.this);
                    JsonArrayRequest jsonrequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?",null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Toast.makeText(jobfeed.this, "Job Accepated", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(jobfeed.this, "Error", Toast.LENGTH_SHORT).show();
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
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.yourentry, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, updateprofile.class);
        i.putExtra("token",token);
        startActivity(i);
        return true;
    }
    
}
