package com.cs160.joleary.catnip;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
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
    Typeface gotham = Typeface.createFromAsset(getContext().getAssets(), "fonts/gotham_book.ttf");
    Typeface gotham_medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/GOTHAM-MEDIUM.TTF");
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
        View background;
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
//        Typeface gotham = Typeface.createFromAsset(getAssets(), "fonts/GOTHAM-BOLD.TTF");
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
            handler.background = row.getRootView();
            row.setTag(handler);
        }
        else {

            handler=(DataHandler)row.getTag();
        }
        MoveDataProvider dataProvider;
        dataProvider=(MoveDataProvider)this.getItem(position);
        handler.poster.setImageDrawable(dataProvider.getMove_poster_resource());
        handler.title.setText(dataProvider.getMove_title());
        handler.title.setTypeface(gotham_medium);
        handler.rating.setText(dataProvider.getMove_rating());
        handler.rating.setTypeface(gotham);
        handler.email.setText(dataProvider.getEmail());
        handler.email.setTypeface(gotham);
        handler.tweet.setText(dataProvider.getTweet());
        handler.tweet.setTypeface(gotham);
        handler.party.setText(dataProvider.getParty());
        handler.party.setTypeface(gotham);
        if (dataProvider.getParty().equals("D")) {
            handler.background.setBackgroundResource(R.color.blue);
            handler.tweet.setText("Democrat");
        } else {
            handler.background.setBackgroundResource(R.color.red);
            handler.tweet.setText("Republican");
        }
        return row;
    }
}



