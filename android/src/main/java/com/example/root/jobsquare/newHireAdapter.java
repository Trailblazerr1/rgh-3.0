package com.example.root.jobsquare;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 2/12/17.
 */

public class newHireAdapter extends BaseAdapter {
    Context context;
    ArrayList<info> infolist;
    public newHireAdapter(Context hirefeed, ArrayList<info> infolist) {
        this.context = hirefeed;
        this.infolist = infolist;
    }

    @Override
    public int getCount() {
        return infolist.size();
    }

    @Override
    public info getItem(int i) {
        return infolist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = View.inflate(context, R.layout.new_hire_adapter,null);
        }
        info in = infolist.get(i);
        TextView name = (TextView)view.findViewById(R.id.avail_name);
        TextView location = (TextView)view.findViewById(R.id.contact);
        name.setText(in.getName());
        location.setText(in.getLocation());
        Button bt = (Button)view.findViewById(R.id.button);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }
}

//{
//@Override
//public Map<String, String> getHeaders () throws AuthFailureError {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("token", token);
//        return params;
//        }
//        };
