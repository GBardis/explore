package com.explore.features.tour.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.tour.domain.FragmentSettable;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourPackageDescriptionFragment extends FragmentSettable implements TourView {

    @BindView(R.id.text_tour_description)
    TextView textViewTourDescription;

    private TourPresenter mTourPresenter;

    private String mParentArg;

    public TourPackageDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour_package_description, container, false);
        ButterKnife.bind(this,v);

        mTourPresenter = new TourPresenterImpl(this);
        mTourPresenter.getTourPackage(mParentArg);

        return v;
    }

    public void setDescription(String description){
        textViewTourDescription.setText(description);
    }

    @Override
    public void showTourPackage(ArrayList<TourUI> tourUIArrayList, TourPackageUI tourPackageUI) {
        textViewTourDescription.setText(tourPackageUI.getDescription());
    }

    @Override
    public void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList) {

    }

    @Override
    public void setStringAttr(String s){
        Timber.tag("FRAGMENT_TALKING").d("Received parent argument: " + s);
        this.mParentArg = s;
    }
}
