package com.explore.features.tour.presentation;

import com.explore.data.db.model.Tour;
import com.explore.data.db.model.TourPackage;
import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageDomain;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

public class TourPresenterImpl implements TourPresenter,TourInteractor.OnTourPackageFinishListener {

    private TourView tourView;
    private TourInteractor tourInteractor;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(TourView tourView) {

        this.tourView = tourView;
        tourInteractor = new TourInteractorImpl();
    }

    @Override
    public void getTourPackage(String tourPackageId) {
        tourInteractor.getTourPackage(this,tourPackageId);
    }

    @Override
    public void onSuccess(ArrayList<TourDomain> tourDomainArrayList, TourPackageDomain tourPackageDomain) {
    }

    @Override
    public void onError() {

    }
}
