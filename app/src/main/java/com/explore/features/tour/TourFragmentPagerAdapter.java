package com.explore.features.tour;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.explore.R;
import com.explore.features.tour.presentation.ReviewListFragment;
import com.explore.features.tour.presentation.TourListFragment;

public class TourFragmentPagerAdapter extends FragmentPagerAdapter {
    Context mContext;

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
                return mContext.getString(R.string.view_pager_tour_tab_title);
            case 1:
                return mContext.getString(R.string.view_pager_review_tab_title);
            default:
                return null;
        }
    }
}
