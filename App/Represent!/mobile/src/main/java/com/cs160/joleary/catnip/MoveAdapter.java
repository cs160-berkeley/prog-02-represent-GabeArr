package com.cs160.joleary.catnip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabrielarreola on 3/3/16.
 */
public class MoveAdapter extends ArrayAdapter {
    List list=new ArrayList();

    public MoveAdapter(Context context, int resource) {
        super(context, resource);
    }


    static class DataHandler
    {
        ImageView poster;
        TextView title;
        TextView rating;
        TextView email;
        TextView tweet;
        TextView party;
    }
    public void add(Object object){
        super.add(object);
        list.add(object);
    }
    public int getCount(){
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        DataHandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.second_layout,parent,false);
            handler=new DataHandler();
            handler.poster=(ImageView)row.findViewById(R.id.move_poster);
            handler.title=(TextView)row.findViewById(R.id.move_title);
            handler.rating=(TextView)row.findViewById(R.id.move_rating);
            handler.email=(TextView)row.findViewById(R.id.email);
            handler.tweet=(TextView)row.findViewById(R.id.tweet);
            handler.party=(TextView)row.findViewById(R.id.party);
            row.setTag(handler);
        }
        else {

            handler=(DataHandler)row.getTag();
        }
        MoveDataProvider dataProvider;

        dataProvider=(MoveDataProvider)this.getItem(position);
        handler.poster.setImageResource(dataProvider.getMove_poster_resource());
        handler.title.setText(dataProvider.getMove_title());
        handler.rating.setText(dataProvider.getMove_rating());
        handler.email.setText(dataProvider.getEmail());
        handler.tweet.setText(dataProvider.getTweet());
        handler.party.setText(dataProvider.getParty());
        return row;
    }
}



