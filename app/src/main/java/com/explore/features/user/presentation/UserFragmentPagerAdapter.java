package com.explore.features.user.presentation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.explore.R;

public class UserFragmentPagerAdapter extends FragmentPagerAdapter {
    Context mContext;

    public UserFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int viewPagerPosition) {
        switch (viewPagerPosition) {
            case 0:
                return new UserProfileFragment();
            case 1:
                return new UserIndexFragment();
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
                return mContext.getString(R.string.view_pager_profile_tab_title);
            case 1:
                return mContext.getString(R.string.view_pager_users_tab_title);
            default:
                return null;
        }
    }
}
