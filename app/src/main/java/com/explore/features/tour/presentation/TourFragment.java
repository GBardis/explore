package com.explore.features.tour.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.explore.R;
import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment implements TourView {

    Button tourDummyFetchButton;
    TourPresenter tourPresenter;

    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour, container, false);
        tourDummyFetchButton = v.findViewById(R.id.button_tour_dummy_fetch);

        tourPresenter = new TourPresenterImpl(this);
        tourDummyFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tourPresenter.getTourPackage();
            }
        });
        return v;
    }

    @Override
    public void showTourPackage(ArrayList<Tour> tourArrayList, TourPackage tourPackage) {

    }
}
