package com.example.luca.testfirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Luca on 13/02/2018.
 */

public class MyAdapter extends ArrayAdapter<User> {
    ArrayList<User> userList = new ArrayList<>();
    private Context context;
    public MyAdapter(Context ctx, int textViewResourceId, ArrayList<User> objects) {
        super(ctx,textViewResourceId,objects);
        userList= objects;
        context = ctx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v=LayoutInflater.from(context).inflate(R.layout.item_user, null);
            User user=userList.get(position);
            TextView textView = (TextView) v.findViewById(R.id.tvId);
            TextView textView2 = (TextView) v.findViewById(R.id.tvProject);
            textView.setText(user.getId().toString());
            textView2.setText(user.getProgetto().toString());
        }

        return v;
    }
}
