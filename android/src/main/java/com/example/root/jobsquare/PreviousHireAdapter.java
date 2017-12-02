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

public class PreviousHireAdapter extends BaseAdapter {
    Context context;
    ArrayList<info> infolist;
    public PreviousHireAdapter(Context previous_reqruitment, ArrayList<info> infolist) {
        this.context = previous_reqruitment;
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
            view = View.inflate(context, R.layout.previous_hire_adapter,null);
        }
        info in = infolist.get(i);
        TextView name = (TextView)view.findViewById(R.id.employee);
        TextView contact = (TextView)view.findViewById(R.id.Contact);
        TextView wage = (TextView)view.findViewById(R.id.wage);
        TextView date = (TextView)view.findViewById(R.id.date);
        name.setText(in.getName());
        wage.setText(in.getWage());
        contact.setText(in.getContact());
        date.setText(in.getDate());
        return view;
    }
}
