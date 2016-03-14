package com.cs160.joleary.catnip;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;

import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat na.me passed in.

//    private Button mFredButton;
//    private Button mLexyButton;
    private Button represent;
    private EditText zipCode1;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;
    private Button locator;
    private TextView appName;

    private Boolean getLocation = false;
    private String zipCodeByLocation;
    private String zipCodeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        appName = (TextView) findViewById(R.id.app_name);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Chunkfive.otf");
        Typeface americanCaptain = Typeface.createFromAsset(getAssets(), "fonts/AmericanCaptain.ttf");
        Typeface norwester = Typeface.createFromAsset(getAssets(), "fonts/norwester.otf");
        Typeface lobster = Typeface.createFromAsset(getAssets(), "fonts/Lobster_1.3.otf");
        Typeface gotham = Typeface.createFromAsset(getAssets(), "fonts/GOTHAM-BOLD.TTF");

//        appName.setTypeface(lobster);
//        appName.setTypeface(norwester);
//        appName.setTypeface(americanCaptain);
//        appName.setTypeface(typeface);
        appName.setTypeface(gotham);
        locator = (Button) findViewById(R.id.locator);
        represent = (Button) findViewById(R.id.represent);
        zipCode1 = (EditText) findViewById(R.id.zip_code);


        zipCode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipCode1.setText("");
                getLocation = false;
            }
        });

        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipCode1.setText(zipCodeByLocation);
                getLocation = true;
            }
        });

        represent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText zipCode = (EditText) findViewById(R.id.zip_code);
                if (getLocation) {
                    zipCodeString = zipCodeByLocation;
                } else {
                    zipCodeString = zipCode.getText().toString();
                }
                Intent toLegAct = new Intent(getBaseContext(), LegislatorsActivity.class);
                toLegAct.putExtra("ZIP_CODE", zipCodeString);
                startActivity(toLegAct);
//                Intent toRepList = new Intent(getBaseContext(), RepresentativeList.class);
//                startActivity(toRepList);
//
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                System.out.println("MAIN ACTIVITY CODE: " + zipCodeString);
//                System.out.println("=====================================================");
//                sendIntent.putExtra("ZIP_CODE", zipCodeString);
//                startService(sendIntent);
            }
        });

        final int REQUEST_LOCATION = 2;
        Location myLocation = null;

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

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

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText = String.valueOf(mLastLocation.getLatitude());
            mLongitudeText =String.valueOf(mLastLocation.getLongitude());
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                zipCodeByLocation = addresses.get(0).getPostalCode();
            } catch (IOException e) {
                Log.d("error", "someOtherMethod()", e);}
        } else { System.out.println(" GOSH DARN IT");}
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

}
