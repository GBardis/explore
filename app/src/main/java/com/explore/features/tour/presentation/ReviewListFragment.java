package com.explore.features.tour.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewListFragment extends Fragment implements TourView {


    public ReviewListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review_list, container, false);

        return v;
    }

    @Override
    public void showTourPackage(ArrayList<TourUI> tourUIArrayList, TourPackageUI tourPackageUI) {

    }
}
