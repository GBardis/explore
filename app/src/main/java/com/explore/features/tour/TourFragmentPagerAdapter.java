package com.explore.features.tour;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.tour.domain.FragmentSettable;
import com.explore.features.tour.presentation.ReviewListFragment;
import com.explore.features.tour.presentation.TourListFragment;
import com.explore.features.tour.presentation.TourPackageDescriptionFragment;

public class TourFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    private String mTourPackageId;
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    // TODO: Create constructor to accept arguments for TourPackage id

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
            case 2:
                return new TourPackageDescriptionFragment();
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
                return mContext.getString(R.string.view_pager_tour_tab_title);
            case 1:
                return "Reviews";
            case 2:
                return mContext.getString(R.string.view_pager_review_tab_title);
            default:
                return null;
        }
    }


    // Overrides for being able to get the current fragment!
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        Log.d("FRAGMENT","CREATED FRAGMENT" + position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("FRAGMENT","DESTROYING FRAGMENT" + position);
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
