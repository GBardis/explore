package com.explore.features.tourpackage.presentation;

import android.content.Context;

import com.explore.features.tourpackage.PresenterObserver;
import com.explore.features.tourpackage.data.TourPackageInteractorImpl;
import com.explore.features.tourpackage.data.TourPackageObservable;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;
import com.explore.features.tourpackage.domain.TourPackagePresenter;
import com.explore.features.tourpackage.domain.TourPackageUI;
import com.explore.features.tourpackage.domain.TourPackageView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TourPackagePresenterImpl extends PresenterObserver implements TourPackagePresenter, TourPackageInteractor.OnTourPackageListFinishListener {
    @Getter
    @Setter
    TourPackageView tourPackageView;
    @Getter
    @Setter
    TourPackageInteractor tourPackageIteractor;

    public TourPackagePresenterImpl(TourPackageView tourPackageView) {
        this.tourPackageView = tourPackageView;
        this.tourPackageIteractor = new TourPackageInteractorImpl();
    }

    @Override
    public void getTourPackages(Context context) {
        getTourPackageIteractor().getTourPackages(this,this, context);
    }

    @Override
    public void onSuccess(List<TourPackageDomain> tourPackageDomainList) {
        List<TourPackageUI> tourPackageUIList = new ArrayList<>();
        for (TourPackageDomain tourPackageDomain : tourPackageDomainList) {
            TourPackageUI tourPackageUI = new TourPackageUI(
                    tourPackageDomain.getName(),
                    tourPackageDomain.getRegion(),
                    tourPackageDomain.getRating()
            );
            tourPackageUIList.add(tourPackageUI);
        }
        getTourPackageView().showTourPackages(tourPackageUIList);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void update(TourPackageObservable tourPackageObservable, Object o) {
        List<TourPackageUI> tourPackageUIList = new ArrayList<>();
        for (TourPackageDomain tourPackageDomain : (List<TourPackageDomain>) o) {
            TourPackageUI tourPackageUI = new TourPackageUI(
                    tourPackageDomain.getName(),
                    tourPackageDomain.getRegion(),
                    tourPackageDomain.getRating()
            );
            tourPackageUIList.add(tourPackageUI);
        }
        getTourPackageView().showTourPackages(tourPackageUIList);
    }

    public TourPackagePresenterImpl() {
    }
}
