package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


/**
 * Created by gabrielarreola on 3/3/16.
 */
public class RepresentativeInfo extends Activity {
    int[]move_poster_resource={R.drawable.feinstein,R.drawable.torres,R.drawable.boxer,};
//    Typeface gotham = Typeface.createFromAsset(getAssets(), "fonts/GOTHAM-BOLD.TTF");
////    Typeface gotham = Typeface.createFromAsset(getAssets(), "fonts/gotham_book.ttf");
//    Typeface gotham_medium = Typeface.createFromAsset(getAssets(), "fonts/GOTHAM-MEDIUM.TTF");
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
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.representative_info);
        move_title=getResources().getStringArray(R.array.move_title);
        end_term_date=getResources().getStringArray(R.array.end_term_date);
        committees=getResources().getStringArray(R.array.committees);
        sponsored_bills=getResources().getStringArray(R.array.sponsored_bills);
        parties=getResources().getStringArray(R.array.party);
        background=(RelativeLayout) findViewById(R.id.backGround);

        name = (TextView) findViewById(R.id.name);
//        committees_list = (TextView) findViewById(R.id.committees);
//        sponsored_bills_list = (TextView) findViewById(R.id.sponsored_bills);
        end_term = (TextView) findViewById(R.id.end_term_date);
        picture = (ImageView) findViewById(R.id.picture);
//        party = (TextView) findViewById(R.id.party);

        Intent intended = getIntent();
        Bundle b = intended.getExtras();
        if (b != null) {
            int position = (int) b.get("position");
            String rName = (String) b.get("name");
            String termination = (String) b.get("end_date");
            String bioGuideId = (String) b.get("bioGuideId");
            String rParty = (String) b.get("party");
            System.out.println("please let this be the problem");
            System.out.println(bioGuideId);

            Drawable d = null;

            try{
                System.out.println("fuck this better match " + bioGuideId);
                InputStream is = (InputStream) new URL("https://theunitedstates.io/images/congress/225x275/F000062.jpg").getContent();
                d = Drawable.createFromStream(is, "src name");
                System.out.println("hot damn");
            } catch (Exception c) {
                System.out.println("hahhaha fuck");
            }

            System.out.println("this is what d looks like from repinfo" + d);
            picture.setImageDrawable(d);

            name.setText(rName);
//            name.setTypeface(gotham);
//            committees_list.setText(committees[position]);
//            sponsored_bills_list.setText(sponsored_bills[position]);
            end_term.setText("term in office ends: " + termination);
//            end_term.setTypeface(gotham_medium);

            if (rParty.equals("D")) {
//                picture.setBackgroundColor(0x4462AD);
                background.setBackgroundResource(R.color.blue);
//                party.setText("Democrat");
            } else {
//                party.setBackgroundColor(0xBA2025);
                background.setBackgroundResource(R.color.red);
//                party.setText("Republican");
            }
//            party.setTypeface(gotham_medium);


        }

    }
}