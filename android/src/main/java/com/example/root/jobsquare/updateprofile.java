package com.example.root.jobsquare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class updateprofile extends AppCompatActivity {
    Spinner jobtype;
    String jtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        jobtype = (Spinner)findViewById(R.id.jobtype);
        String[] items = new String[]{"Carpanter", "Cook", "Laborer","Plumber","Electrician","Sweeper"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        jobtype.setAdapter(adapter);
        jobtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                jtype = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                jtype = null;
            }
        });
    }
}
