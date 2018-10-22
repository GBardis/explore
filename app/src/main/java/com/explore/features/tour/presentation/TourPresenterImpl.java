package com.explore.features.tour.presentation;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;
import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

public class TourPresenterImpl implements TourPresenter,TourInteractor.OnTourPackageFinishListener {

    TourView tourView;
    TourInteractor tourInteractor;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(TourView tourView) {

        this.tourView = tourView;
        tourInteractor = new TourInteractorImpl();
    }

    @Override
    public void getTourPackage() {
        tourInteractor.getTourPackage(this);
    }

    @Override
    public void onSuccess(ArrayList<Tour> tourArrayList, TourPackage tourPackage) {
    }

    @Override
    public void onError() {

    }
}
