package com.cs160.joleary.catnip;

//package com.example.guest.congress.ui;

/**
 * Created by gabrielarreola on 3/11/16.
 */
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import android.widget.ListAdapter;

//import com.example.guest.congress.R;
//import com.example.guest.congress.adapters.ListAdapter;
//import com.example.guest.congress.models.Legislator;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;



//import butterknife.ButterKnife;

public class LegislatorsActivity extends ListActivity {
    public static final String TAG = LegislatorsActivity.class.getSimpleName();

    private ArrayList<String> mBillsList;

    private ArrayList<Legislator> mLegislators;

    private ArrayList<String> mLegislatorList;

    private String mZipcode;

    private static ListAdapter mAdapter;

    ListView listView;
    int[]move_poster_resource={R.drawable.feinstein,R.drawable.torres,R.drawable.boxer,};
    //            R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,};
//    String[]move_title;
//    String[]move_rating;
//    String[]email;
//    String[]tweet;
//    String[]party;
    MoveAdapter adapter;

    String bioGuideId = "F000062";



    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        listView=(ListView)findViewById(R.id.list_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mZipcode = extras.getString("ZIP_CODE");
        Toast.makeText(this, mZipcode, Toast.LENGTH_LONG).show();
        adapter=new MoveAdapter(getApplicationContext(),R.layout.second_layout);
        listView.setAdapter(adapter);

        Runnable displayInfo = new Runnable() {
            @Override
            public void run() {
                /*  Old way of populating before using ListAdapter
                mLegislatorList = new ArrayList<String>();
                for (Legislator legislator : mLegislators) {
                    String firstName = legislator.getFirstName();
                    String lastName = legislator.getLastName();
                    String party = legislator.getParty();
                    String title = legislator.getTitle();
                    String info = title + " " + firstName + " " + lastName + "  (" + party + ")";
                    mLegislatorList.add(info);
                }
                mAdapter=new ArrayAdapter<String>(LegislatorsActivity.this, android.R.layout.simple_list_item_1, mLegislatorList);
                */

//                mAdapter = new ListAdapter(LegislatorsActivity.this, mLegislators);
                setListAdapter(mAdapter);
            }

        };

        getLegislators(mZipcode, displayInfo);
        getBills(bioGuideId, displayInfo);


        //I DUNNO IF I SHOULD BE ADDING THIS PART
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), position + "is selected", Toast.LENGTH_SHORT).show();
                String name = mLegislators.get(position).getFirstName() + " " + mLegislators.get(position).getLastName();
                String termination = mLegislators.get(position).getTerm_end();
                String bioGuide = mLegislators.get(position).getBioguideId();
                String party = mLegislators.get(position).getParty();

                Intent myIntent = new Intent(view.getContext(), RepresentativeInfo.class);
                myIntent.putExtra("bioGuideId", bioGuide);
                myIntent.putExtra("end_date", termination);
                myIntent.putExtra("position", position);
                myIntent.putExtra("name", name);
                myIntent.putExtra("party", party);
//                myIntent.putExtra("picture", picture);
//                myIntent.putExtra("picture", picture);
                startActivity(myIntent);
                System.out.println("this is my position" + position);
            }
        });


    }


    public void getLegislators(String zipcode, final Runnable runnable) {
        String apiKey = "04430deb1c4648c98fb7f23905069d46";

        String legislatorsURL = "https://congress.api.sunlightfoundation.com/legislators/locate?apikey=" + apiKey + "&zip=" + zipcode;

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(legislatorsURL)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);

                        if (response.isSuccessful()) {
                            mLegislators = getLegislatorDetails(jsonData);
                            runOnUiThread(runnable);
                        } else {
                            alertUserAboutError();
                        }

                    } catch (IOException e) {
                        Log.e(TAG, getString(R.string.hello_world), e);
                    } catch (JSONException e) {
                        Log.e(TAG, getString(R.string.hello_world), e);
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.hello_world, Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<Legislator> getLegislatorDetails(String jsonData) throws JSONException {

        ArrayList<Legislator> legislatorArrayList = new ArrayList<>();

        JSONObject legislatorsData = new JSONObject(jsonData);

        String legislatorsInfo = legislatorsData.getString("results");

        Log.i("legislators Info", legislatorsInfo);
        JSONArray jsonArray = new JSONArray(legislatorsInfo);


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonPart = jsonArray.getJSONObject(i);
            String bioguide_id = jsonPart.getString("bioguide_id");
            bioGuideId = bioguide_id;
            String firstName = jsonPart.getString("first_name");
            String lastName = jsonPart.getString("last_name");
            String party = jsonPart.getString("party");
            String title = jsonPart.getString("title");
            String email = jsonPart.getString("oc_email");
            String phone = jsonPart.getString("phone");
            String office = jsonPart.getString("office");
            String website = jsonPart.getString("website");
            String termEnd = jsonPart.getString("term_end");


            Legislator thisLegislator = new Legislator(bioguide_id, firstName, lastName, party, title, email, phone, office, mZipcode, website, termEnd);
            legislatorArrayList.add(thisLegislator);

            Drawable d = null;

            try{
                System.out.println("we better be using the same Ids " + thisLegislator.getBioguideId());
                InputStream is = (InputStream) new URL("https://theunitedstates.io/images/congress/225x275/" + thisLegislator.getBioguideId() + ".jpg").getContent();
                d = Drawable.createFromStream(is, "src name");
            } catch (Exception c) {
                System.out.println("why is this happening");
                System.out.println("hahhaha fuck");
            }

            System.out.println("this is what d looks like" + d);


            final MoveDataProvider dataProvider=new MoveDataProvider(d,
                    thisLegislator.getFirstName() + " " + thisLegislator.getLastName(),thisLegislator.getWebsite(),
                    thisLegislator.getEmail(), thisLegislator.getTitle(), thisLegislator.getParty());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.add(dataProvider);
                }
            });

        }
        return legislatorArrayList;
    }


    public void getBills(String bioGuideId, final Runnable runnable) {
        String apiKey = "04430deb1c4648c98fb7f23905069d46";

        String legislatorsURL = "https://congress.api.sunlightfoundation.com/bills/search?apikey=" + apiKey + "&sponsor_id=" + bioGuideId;

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(legislatorsURL)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);

                        if (response.isSuccessful()) {
                            mBillsList = getBillDetails(jsonData);
                            runOnUiThread(runnable);
                        } else {
                            alertUserAboutError();
                        }

                    } catch (IOException e) {
                        Log.e(TAG, getString(R.string.hello_world), e);
                    } catch (JSONException e) {
                        Log.e(TAG, getString(R.string.hello_world), e);
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.hello_world, Toast.LENGTH_LONG).show();
        }
    }





    private ArrayList<String> getBillDetails(String jsonData) throws JSONException {

        ArrayList<String> billArrayList = new ArrayList<>();

        JSONObject billsData = new JSONObject(jsonData);

        String billsInfo = billsData.getString("results");

        Log.i("Bills Info", billsInfo);
        JSONArray jsonArray = new JSONArray(billsInfo);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonPart = jsonArray.getJSONObject(i);
            String billTitle = jsonPart.getString("official_title");
            billArrayList.add(billTitle);
        }

        return billArrayList;
    }









    private void alertUserAboutError() {
        System.out.println("BRO YOU MESSED UP");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        System.out.println("this is the pos from below" + position);
        super.onListItemClick(l, v, position, id);
        Legislator thisLegislator = (Legislator) mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        System.out.println(thisLegislator.getBioguideId());
        bundle.putString("bioguide_id", thisLegislator.getBioguideId());
        bundle.putString("first_name", thisLegislator.getFirstName());
        bundle.putString("last_name", thisLegislator.getLastName());
        bundle.putString("party", thisLegislator.getParty());
        bundle.putString("title", thisLegislator.getTitle());
        bundle.putString("oc_email", thisLegislator.getEmail());
        bundle.putString("phone", thisLegislator.getPhone());
        bundle.putString("office", thisLegislator.getOffice());
        bundle.putString("zipcode", thisLegislator.getZipcode());
        bundle.putString("website", thisLegislator.getWebsite());

//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);

    }


    //I DUNNO IF I SHOULD BE ADDING THIS
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
