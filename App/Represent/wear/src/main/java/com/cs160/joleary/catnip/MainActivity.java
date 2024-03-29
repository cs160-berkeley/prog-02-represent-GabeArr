package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button mFeedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFeedBtn = (Button) findViewById(R.id.feed_btn);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            Intent toRepresentatives = new Intent(MainActivity.this, representativeScreen.class);
            toRepresentatives.putExtra("position", 0);
            startActivity(toRepresentatives);
//            String zipCode = extras.getString("ZIP_CODE");
//            System.out.println("MAIN ACTIVITY (WEAR) ZIPCODE: " + zipCode);
//            if(zipCode == ""){
//                mFeedBtn.setText("hahahaha");
//            } else {
//                mFeedBtn.setText(zipCode);
//            }
        }

        mFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                startService(sendIntent);
            }
        });
    }
}
