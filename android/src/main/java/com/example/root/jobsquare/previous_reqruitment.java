package com.example.root.jobsquare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class previous_reqruitment extends AppCompatActivity {
    JSONArray js;
    ListView lv;
    ArrayList<info> infolist;
    PreviousHireAdapter pha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_reqruitment);
        lv = (ListView)findViewById(R.id.list3);
        infolist = new ArrayList<>();
        try {
            js = new JSONArray(getIntent().getStringExtra("previous"));
            for(int i=0;i<js.length();i++){
                JSONObject x = js.getJSONObject(i);
                String name,wage,contact,date;
                name = x.getString("name");
                wage = x.getString("wage");
                contact = x.getString("contact");
                date = x.getString("date");

                info in = new info();
                in.setContact(contact);
                in.setName(name);
                in.setWage(wage);
                in.setDate(date);
                infolist.add(in);
            }
            pha = new PreviousHireAdapter(previous_reqruitment.this, infolist);
            lv.setAdapter(pha);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
