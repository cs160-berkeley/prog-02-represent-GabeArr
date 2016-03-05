package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gabrielarreola on 3/3/16.
 */
public class RepresentativeInfo extends Activity {
    int[]move_poster_resource={R.drawable.feinstein,R.drawable.torres,R.drawable.boxer,};
    String[] move_title;
    String[] end_term_date;
    String[] committees;
    String[] sponsored_bills;
    String[] parties;
    TextView name;
    TextView committees_list;
    TextView sponsored_bills_list;
    TextView end_term;
    TextView party;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.representative_info);
        move_title=getResources().getStringArray(R.array.move_title);
        end_term_date=getResources().getStringArray(R.array.end_term_date);
        committees=getResources().getStringArray(R.array.committees);
        sponsored_bills=getResources().getStringArray(R.array.sponsored_bills);
        parties=getResources().getStringArray(R.array.party);

        name = (TextView) findViewById(R.id.name);
        committees_list = (TextView) findViewById(R.id.committees);
        sponsored_bills_list = (TextView) findViewById(R.id.sponsored_bills);
        end_term = (TextView) findViewById(R.id.end_term_date);
        picture = (ImageView) findViewById(R.id.picture);
        party = (TextView) findViewById(R.id.party);

        Intent intended = getIntent();
        Bundle b = intended.getExtras();
        if (b != null) {
            int position = (int) b.get("position");
            name.setText(move_title[position]);
            committees_list.setText(committees[position]);
            sponsored_bills_list.setText(sponsored_bills[position]);
            end_term.setText(end_term_date[position]);
            picture.setImageResource(move_poster_resource[position]);
            party.setText(parties[position]);
        }

    }
}