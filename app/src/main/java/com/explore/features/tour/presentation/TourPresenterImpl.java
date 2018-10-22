package com.explore.features.tour.presentation;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPresenter;

import java.util.ArrayList;

public class TourPresenterImpl implements TourPresenter,TourInteractor.OnTourPackageFinishListener {

    @Override
    public void getTourPackage(ArrayList<Tour> tourArrayList, TourPackage tourPackage) {

    }

    @Override
    public void onSuccess(ArrayList<Tour> tourArrayList, TourPackage tourPackage) {
        int i=0;
    }

    @Override
    public void onError() {

    }
}
