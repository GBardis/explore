package com.explore.features.tourpackage.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.tourpackage.domain.TourPackageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourPackageFragment extends Fragment implements TourPackageView {


    public TourPackageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tour_package, container, false);
    }

}
