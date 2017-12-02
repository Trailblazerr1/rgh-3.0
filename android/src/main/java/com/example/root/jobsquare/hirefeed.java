package com.example.root.jobsquare;

import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class hirefeed extends AppCompatActivity {
    JSONArray guys;
    ListView lv;
    ArrayList<info> infolist;
    newHireAdapter nha;
    String email;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirefeed);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        lv = (ListView)findViewById(R.id.list2);
        infolist = new ArrayList<>();
        try {
            guys = new JSONArray(getIntent().getStringExtra("job_avail_guys"));
            for(int i=0;i<guys.length();i++){
                JSONObject x  = guys.getJSONObject(i);
                String name = x.getString("name");
                email = x.getString("email");
                String contact = x.getString("contact");

                info in = new info();
                in.setName(name);
                in.setEmail(email);
                in.setContact(contact);
                infolist.add(in);
            }
            nha = new newHireAdapter(hirefeed.this,infolist);
            lv.setAdapter(nha);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    RequestQueue queue = Volley.newRequestQueue(hirefeed.this);
                    JsonArrayRequest jsonrequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.43.51/api-token-auth?",null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Toast.makeText(hirefeed.this, "Successful", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(hirefeed.this, "Error", Toast.LENGTH_SHORT).show();
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
}
