package com.explore.features.tour.presentation;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.reviewnew.presentation.ReviewNewFragment;
import com.explore.features.tour.TourFragmentPagerAdapter;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;
import com.explore.rest.GooglePlacesApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class TourFragment extends Fragment implements TourView, IsToolbarSetter {

    //@BindView(R.id.text_tour_tourpackage_name)
    //TextView mTextViewTourPackageName;

    private TourPresenter mTourPresenter;

    @BindView(R.id.text_tour_tourpackage_description)
    TextView mTextViewDescription;
    @BindView(R.id.text_tour_tourpackage_area)
    TextView mTextViewDArea;
    @BindView(R.id.text_tour_tourpackage_rating)
    TextView mTextViewDRating;


//    @BindView(R.id.text_tour_tourpackage_rating)
//    TextView mTextViewTourPackageRating;

    @BindView(R.id.tab_tour_fragment)
    TabLayout tourTabLayout;

    @BindView(R.id.view_pager_tour_fragment)
    ViewPager tourViewPager;

    @BindView(R.id.fab_tour_fragment_transition_reviewnew)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.image_tour_tourpackage_photo)
    ImageView mImageViewTourPackagePhoto;

    public static List<String> loaderList = new ArrayList<>();

    @Getter
    private GooglePlacesApiClient googlePlacesApiClient;


    Bundle bundle;
    com.explore.features.tourpackage.domain.TourPackageUI mParentArg;
    com.explore.features.tourpackage.domain.TourPackageUI tourPackageUI;

    TourFragmentPagerAdapter tourFragmentPagerAdapter;

    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_tour, container, false);
        ButterKnife.bind(this, v);

        // TODO: maybe refactor to sync with parent bundle
        bundle = new Bundle();

        if (getArguments() != null) {
            mParentArg = getArguments().getParcelable("TOUR_PACKAGE");
        }

        bundle.putParcelable("TOUR_PACKAGE", mParentArg);

        mTourPresenter = new TourPresenterImpl(getActivity(), this);
        tourPackageUI = bundle.getParcelable("TOUR_PACKAGE");
        mTourPresenter.getTourPackage(getActivity(), tourPackageUI.getId(), false);
        tourFragmentPagerAdapter = new TourFragmentPagerAdapter(getChildFragmentManager(), getActivity(), bundle);

        tourTabLayout.setupWithViewPager(tourViewPager);
        tourViewPager.setAdapter(tourFragmentPagerAdapter);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewNewFragment.ReviewNewFragmentListener reviewNewFragmentListener = (ReviewNewFragment.ReviewNewFragmentListener) getActivity();
                reviewNewFragmentListener.transitionToReviewNewFragment(bundle);
            }
        });

        return v;
    }

    @Override
    public void showTourPackage(TourPackageUI tourPackageUI) {
        setToolbarTitle(getActivity(), tourPackageUI.getName());
        mTextViewDescription.setText(tourPackageUI.getName());

        mTextViewDArea.setText(tourPackageUI.getArea());
        mTextViewDArea.setTextColor(Color.parseColor(tourPackageUI.getRegionColor()));

        mTextViewDRating.setText(String.valueOf(tourPackageUI.getRating()));
        mTextViewDRating.setTextColor(Color.parseColor(tourPackageUI.getRatingColor()));
        googlePlacesApiClient = new GooglePlacesApiClient(getActivity());
        googlePlacesApiClient.tourPackageHasImage(mParentArg, mImageViewTourPackagePhoto, getActivity());
    }


    @Override
    public void showTourList(ArrayList<TourUI> tourUIArrayList) {

    }

    @Override
    public void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList) {

    }

    @Override
    public void setToolbarTitle(final Activity activity, final String title) {
        // https://stackoverflow.com/questions/12850143/android-basics-running-code-in-the-ui-thread
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }


    public interface TourFragmentListener {
        void transitionToTourFragment(Bundle bundle);
    }
}
