package com.explore.features.tour;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.explore.R;
import com.explore.features.tour.presentation.ReviewListFragment;
import com.explore.features.tour.presentation.TourListFragment;

public class TourFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TourFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int viewPagerPosition) {

        switch (viewPagerPosition) {
            case 0:
                return new TourListFragment();
            case 1:
                return new ReviewListFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.tab_tour_tours);
            case 1:
                return mContext.getString(R.string.tab_tour_reviews);
            default:
                return null;
        }
    }
}
