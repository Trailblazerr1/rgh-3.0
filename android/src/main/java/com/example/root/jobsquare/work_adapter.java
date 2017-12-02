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

public class work_adapter extends BaseAdapter{
    Context context;
    ArrayList<info> infolist;
    public work_adapter(Context jobfeed, ArrayList<info> infolist) {
        this.context = jobfeed;
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
            view = View.inflate(context, R.layout.work_adapter,null);
        }
        info in = infolist.get(i);
        TextView name = (TextView)view.findViewById(R.id.employer);
        TextView location = (TextView)view.findViewById(R.id.location);
        TextView wage = (TextView)view.findViewById(R.id.wage);
        TextView date = (TextView)view.findViewById(R.id.date);
        name.setText(in.getName());
        wage.setText(in.getWage());
        location.setText(in.getLocation());
        date.setText(in.getDate());
        Button bt = (Button)view.findViewById(R.id.accept);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
