package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;

public class MainActivity extends Activity {
    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

//    private Button mFredButton;
//    private Button mLexyButton;
    private Button represent;
    private EditText zipCode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final EditText zipCode = (EditText) findViewById(R.id.zip_code);
//        final String zipCodeString = zipCode.getText().toString();
//        System.out.println("THIS IS MY ZIPCODE: " + zipCodeString);

        represent = (Button) findViewById(R.id.represent);
        zipCode1 = (EditText) findViewById(R.id.zip_code);
        zipCode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipCode1.setText("");
            }
        });



        represent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText zipCode = (EditText) findViewById(R.id.zip_code);
                final String zipCodeString = zipCode.getText().toString();
                Intent toRepList = new Intent(getBaseContext(), RepresentativeList.class);
                startActivity(toRepList);

                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                System.out.println("MAIN ACTIVITY CODE: " + zipCodeString);
                System.out.println("=====================================================");
                sendIntent.putExtra("ZIP_CODE", zipCodeString);
                startService(sendIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
