package com.explore.features.tour.presentation;

import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageDomain;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

public class TourPresenterImpl implements TourPresenter,TourInteractor.OnTourPackageFinishListener {

    private TourView tourView;
    private TourInteractor mTourInteractor;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(TourView tourView) {

        this.tourView = tourView;
        mTourInteractor = new TourInteractorImpl();
    }

    @Override
    public void getTourPackage(String tourPackageId) {
        mTourInteractor.getTourPackage(this,tourPackageId);
    }

    @Override
    public void onSuccess(ArrayList<TourDomain> tourDomainArrayList, TourPackageDomain tourPackageDomain) {
    }

    @Override
    public void onError() {

    }
}
