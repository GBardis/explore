package com.explore.features.tour.presentation;


import android.app.Activity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment implements TourView, IsToolbarSetter {

    //@BindView(R.id.text_tour_tourpackage_name)
    //TextView mTextViewTourPackageName;

    private TourPresenter mTourPresenter;

    @BindView(R.id.text_tour_tourpackage_description)
    TextView mTextViewDescription;

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

    @Getter
    private GooglePlacesApiClient googlePlacesApiClient;

    Bundle bundle;
    com.explore.features.tourpackage.domain.TourPackageUI mParentArg;

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

        // TODO: maybe refactor to sync with parent bundle
        bundle = new Bundle();

        if (getArguments() != null) {
            mParentArg = getArguments().getParcelable("TOUR_PACKAGE");
        }

        bundle.putString("TOUR_PACKAGE_ID", mParentArg.getId());

        mTourPresenter = new TourPresenterImpl(getActivity(), this);
        mTourPresenter.getTourPackage(getActivity(), bundle.getString("TOUR_PACKAGE_ID"));
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
        googlePlacesApiClient = new GooglePlacesApiClient(getActivity());
        googlePlacesApiClient.getPhotos(mParentArg.getPlaceId(), mImageViewTourPackagePhoto);
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
