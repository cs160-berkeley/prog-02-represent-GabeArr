package com.cs160.joleary.catnip;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String FRED_FEED = "/Fred";
    private static final String LEXY_FEED = "/Lexy";
    private static final String ZIP_CODE = "/zipCode";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)

        if( messageEvent.getPath().equalsIgnoreCase( ZIP_CODE )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            System.out.println("WATCHLISTENERSERVICE VALUE:  " + value);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("ZIP_CODE", value);
            Log.d("T", "about to start watch MainActivity with ZIP_CODE: " + value);
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }



    }
}