package com.explore.features.user.presentation;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.base.ExploreApplication;
import com.explore.features.IsToolbarSetter;
import com.explore.features.user.domain.UserDomain;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment implements IsToolbarSetter {
    @BindView(R.id.viewpager_user_fragment)
    ViewPager viewPager;
    @BindView(R.id.tab_user_fragment)
    TabLayout tabLayout;

    @BindString(R.string.user_fragment_title)
    String userFragmentTitle;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, v);
        setToolbarTitle(getActivity(), userFragmentTitle);

        // Create an adapter that knows which fragment should be shown on each page
        UserFragmentPagerAdapter adapter = new UserFragmentPagerAdapter(getChildFragmentManager(), getActivity());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager);
        UserDomain currentUser = ExploreApplication.getCurrentUser();
        return v;
    }

    @Override
    public void setToolbarTitle(Activity activity, String title) {
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }

    public interface UserFragmentListener {
        void transitionToUserFragment();
    }
}
