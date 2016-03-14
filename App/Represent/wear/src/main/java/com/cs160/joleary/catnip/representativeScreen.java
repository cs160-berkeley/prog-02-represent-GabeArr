package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gabrielarreola on 3/3/16.
 */
public class representativeScreen extends Activity {
//    int[]pictures={R.drawable.feinstein,R.drawable.torres,R.drawable.boxer,};
//    String[] names;
//    String[] parties;
//
//    TextView name;
//    TextView party;
//    ImageView picture;
    private GridViewPager screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.representative_screen);
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new SampleGridPagerAdapter(this, getFragmentManager()));

        final int col = pager.getPageColumnMargin();
        System.out.println("==============================================");
        System.out.println("From the REPRESENTATIVE SCREEN: " + col);

//        screen = (GridViewPager) findViewById(R.id.pager);

        pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("column", col);
                startService(sendIntent);
            }
        });
        }
//        names = getResources().getStringArray(R.array.names);
//        parties = getResources().getStringArray(R.array.party);
//
//        name = (TextView) findViewById(R.id.name);
//        party = (TextView) findViewById(R.id.party);
//        picture = (ImageView) findViewById(R.id.representative);
//
//        Intent intended = getIntent();
//        Bundle b = intended.getExtras();
//        if (b != null) {
//            int position = (int) b.get("position");
//            name.setText(names[position]);
//            party.setText(parties[position]);
//            picture.setImageResource(pictures[position]);
//        }

}
