package com.explore.features.tour;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.explore.R;
import com.explore.data.db.model.Tour;
import com.explore.features.tour.presentation.ReviewListFragment;
import com.explore.features.tour.presentation.TourListFragment;
import com.explore.features.tour.presentation.TourPackageDescriptionFragment;

public class TourFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    Bundle mParentBundle;
    private String mTourPackageId;
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    // TODO: Create constructor to accept arguments for TourPackage id

    public TourFragmentPagerAdapter(FragmentManager fm, Context context, Bundle parentBundle) {
        super(fm);
        this.mContext = context;
        this.mParentBundle = parentBundle;
    }

    @Override
    public Fragment getItem(int viewPagerPosition) {

        switch (viewPagerPosition) {
            case 0:
                TourListFragment tfr = new TourListFragment();
                tfr.setArguments(mParentBundle);
                return tfr;
            case 1:
                ReviewListFragment rfr = new ReviewListFragment();
                rfr.setArguments(mParentBundle);
                return rfr;
            case 2:
                TourPackageDescriptionFragment tpfr = new TourPackageDescriptionFragment();
                tpfr.setArguments(mParentBundle);
                return tpfr;
            default:
                return null;
        }
    }

    // TODO: set this to dynamic count

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // TODO: Change from static strings to @string
        switch (position) {
            case 0:
                return mContext.getString(R.string.view_pager_tour_tour_tab_title);
            case 1:
                return mContext.getString(R.string.view_pager_tour_review_tab_title);
            case 2:
                return mContext.getString(R.string.view_pager_tour_description_tab_title);
            default:
                return null;
        }
    }


    // Overrides for being able to get the current fragment!
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
