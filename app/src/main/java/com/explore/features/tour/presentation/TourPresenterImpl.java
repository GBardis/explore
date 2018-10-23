package com.explore.features.tour.presentation;

import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageDomain;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TourPresenterImpl implements TourPresenter, TourInteractor.OnTourPackageFinishListener {

    private TourView mTourView;
    private TourInteractor mTourInteractor;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(TourView tourView) {

        this.mTourView = tourView;
        mTourInteractor = new TourInteractorImpl();
    }

    @Override
    public void getTourPackage(String tourPackageId) {
        mTourInteractor.getTourPackage(this, tourPackageId);
    }

    @Override
    public void onSuccess(ArrayList<TourDomain> tourDomainArrayList, TourPackageDomain tourPackageDomain) {
        ArrayList<TourUI> tourUIArrayList = new ArrayList<>();

        for (TourDomain tourDomain : tourDomainArrayList) {
            tourUIArrayList.add(new TourUI(tourDomain.getName(), tourDomain.getRating()));
        }

        mTourView.showTourPackage(
                tourUIArrayList,
                new TourPackageUI(tourPackageDomain.getName(),
                        tourPackageDomain.getArea(),
                        tourPackageDomain.getRating()));
    }

    @Override
    public void onError() {

    }
}
