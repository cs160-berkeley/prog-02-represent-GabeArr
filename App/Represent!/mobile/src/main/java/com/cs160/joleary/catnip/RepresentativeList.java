package com.cs160.joleary.catnip;

/**
 * Created by gabrielarreola on 3/3/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class RepresentativeList extends Activity {
    ListView listView;
    int[]move_poster_resource={R.drawable.feinstein,R.drawable.torres,R.drawable.boxer,};
//            R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,R.drawable.boxer,};
    String[]move_title;
    String[]move_rating;
    String[]email;
    String[]tweet;
    String[]party;
    MoveAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        listView=(ListView)findViewById(R.id.list_view);
        move_rating=getResources().getStringArray(R.array.move_rating);
        move_title=getResources().getStringArray(R.array.move_title);
        email=getResources().getStringArray(R.array.email);
        tweet=getResources().getStringArray(R.array.tweet);
        party=getResources().getStringArray(R.array.party);


        int i=0;
        adapter=new MoveAdapter(getApplicationContext(),R.layout.second_layout);
        listView.setAdapter(adapter);
        for (String title:move_title) {
            MoveDataProvider dataProvider=new MoveDataProvider(move_poster_resource[i],
                    title,move_rating[i], email[i], tweet[i], party[i]);
            adapter.add(dataProvider);
            i++;

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                System.out.println("=================================================");
                System.out.println(position);
                Toast.makeText(getBaseContext(), position + "is selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(view.getContext(), RepresentativeInfo.class);
                myIntent.putExtra("position", position);
                startActivity(myIntent);
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
