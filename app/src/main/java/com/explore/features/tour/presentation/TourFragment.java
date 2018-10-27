package com.explore.features.tour.presentation;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.tour.TourFragmentPagerAdapter;
import com.explore.features.tour.domain.FragmentSettable;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment implements TourView, IsToolbarSetter {

    //@BindView(R.id.text_tour_tourpackage_name)
    //TextView mTextViewTourPackageName;

    private TourPresenter mTourPresenter;

    @BindView(R.id.text_tour_tourpackage_description)
    TextView mTextViewDescription;

    @BindView(R.id.text_tour_tourpackage_rating)
    TextView mTextViewTourPackageRating;

    @BindView(R.id.tab_tour_fragment)
    TabLayout tourTabLayout;

    @BindView(R.id.view_pager_tour_fragment)
    ViewPager tourViewPager;

    private Fragment mCurrentFragment;
    private TourPackageUI mTourPackageUI;


    TourFragmentPagerAdapter tourFragmentPagerAdapter;

    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour, container, false);
        ButterKnife.bind(this, v);

        mTourPresenter = new TourPresenterImpl(this);
        mTourPresenter.getTourPackage("2");
        tourFragmentPagerAdapter = new TourFragmentPagerAdapter(getChildFragmentManager(), getActivity());

        tourTabLayout.setupWithViewPager(tourViewPager);
        tourViewPager.setAdapter(tourFragmentPagerAdapter);

        return v;
    }

    @Override
    public void showTourPackage(ArrayList<TourUI> tourUIArrayList, TourPackageUI tourPackageUI) {
        setToolbarTitle(getActivity(), tourPackageUI.getName());

        mTextViewDescription.setText(tourPackageUI.getDescription());
    }

    @Override
    public void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList) {

    }

    @Override
    public void setToolbarTitle(Activity activity, String title) {
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }

    public void onAttachFragment(Fragment mCurrentFragment) {
        Log.d("FRAGMENT_ATTACH", "Attached Fragment!" + mCurrentFragment.getClass().toString());

        // TODO: TourPackageID should go here when available
        ((FragmentSettable) mCurrentFragment).setStringAttr("2");
    }

    public interface TourFragmentListener {
        void transitionToTourFragment();
    }
}
