package com.cs160.joleary.catnip;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.view.Gravity;
import android.content.res.Resources;

import java.util.List;


/**
 * Created by gabrielarreola on 3/3/16.
 */

/**
 * Constructs fragments as requested by the GridViewPager. For each row a
 * different background is provided.
 */
public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List mRows;
    public SampleGridPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    static final int[] BG_IMAGES = new int[] {R.drawable.food_bg_160, R.drawable.torres, R.drawable.boxer};

    // A simple container for static data in each page
    private static class Page {
        // static resources
        int titleRes;
        int textRes;
        int cardGravity = Gravity.BOTTOM;
        boolean expansionEnabled = true;
        float expansionFactor = 1.0f;
        int expansionDirection = CardFragment.EXPAND_DOWN;


        public Page(int titleRes, int textRes, int gravity) {
            this.titleRes = titleRes;
            this.textRes = textRes;
            this.cardGravity = gravity;
        }
    }

    // Create a static set of pages in a 2D array
    private final Page[][] PAGES = {
            {new Page(R.string.feinstein, R.string.party, Gravity.CENTER_VERTICAL),
            new Page(R.string.torres, R.string.party, Gravity.CENTER_VERTICAL),
            new Page(R.string.boxer, R.string.party, Gravity.CENTER_VERTICAL)}
    };

    // Obtain the UI fragment at the specified position
    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        String title = page.titleRes != 0 ? mContext.getString(page.titleRes) : null;
        String text = page.textRes != 0 ? mContext.getString(page.textRes) : null;
        CardFragment fragment = CardFragment.create(title, text);

        // Advanced settings (card gravity, card expansion/scrolling)
        fragment.setCardGravity(page.cardGravity);
        fragment.setExpansionEnabled(page.expansionEnabled);
        fragment.setExpansionDirection(page.expansionDirection);
        fragment.setExpansionFactor(page.expansionFactor);
        return fragment;
//
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return mContext.getResources().getDrawable(
                (BG_IMAGES[row % BG_IMAGES.length]), null);
    }

    // Obtain the background image for the specific page
    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        if( row == 0 & column==0) {
            // Place image at specified position
            return mContext.getResources().getDrawable(R.drawable.feinstein, null);
        } else if ( row == 0 & column == 1) {
            return mContext.getResources().getDrawable(R.drawable.torres, null);
        }else if (row == 0 & column == 2) {
            return mContext.getResources().getDrawable(R.drawable.boxer, null);
        } else {
            // Default to background image for row
            return mContext.getResources().getDrawable(R.drawable.food_bg_160, null);
//            return GridPagerAdapter.BACKGROUND_NONE;
        }
    }
    // Obtain the number of pages (vertical)
    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    // Obtain the number of pages (horizontal)
    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }

}




