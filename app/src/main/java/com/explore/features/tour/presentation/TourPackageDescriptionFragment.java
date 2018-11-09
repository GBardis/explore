package com.explore.features.tour.presentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explore.R;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;
import com.explore.features.tourpackage.domain.TourPackageUI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourPackageDescriptionFragment extends Fragment implements TourView {

    @BindView(R.id.text_tour_description)
    TextView textViewTourDescription;

    private TourPresenter mTourPresenter;

    private String mParentArg;

    TourPackageUI tourPackageUI;

    public TourPackageDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour_package_description, container, false);
        ButterKnife.bind(this, v);

        if (getArguments() != null) {
            tourPackageUI = getArguments().getParcelable("TOUR_PACKAGE");
        }


        showTourPackageLocal(tourPackageUI);

        return v;
    }

    public void setDescription(String description) {
        textViewTourDescription.setText(description);
    }

    public void showTourPackageLocal(TourPackageUI tourPackageUI) {
        textViewTourDescription.setText(tourPackageUI.getName());
    }

    @Override
    public void showTourPackage(com.explore.features.tour.domain.TourPackageUI tourPackageUI) {

    }

    @Override
    public void showTourList(ArrayList<TourUI> tourUIArrayList) {

    }

    @Override
    public void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList) {

    }

}
