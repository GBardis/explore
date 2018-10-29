package com.explore.features.tourpackage.presentation;

import com.explore.features.tourpackage.data.TourPackageIteractorImpl;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;
import com.explore.features.tourpackage.domain.TourPackagePresenter;
import com.explore.features.tourpackage.domain.TourPackageUI;
import com.explore.features.tourpackage.domain.TourPackageView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TourPackagePresenterImpl implements TourPackagePresenter, TourPackageInteractor.OnTourPackageListFinishListener {
    @Getter
    @Setter
    TourPackageView tourPackageView;
    @Getter
    @Setter
    TourPackageInteractor tourPackageIteractor;

    public TourPackagePresenterImpl(TourPackageView tourPackageView) {
        this.tourPackageView = tourPackageView;
        this.tourPackageIteractor = new TourPackageIteractorImpl();
    }

    @Override
    public void getTourPackages() {
        getTourPackageIteractor().getTourPackages(this);
    }

    @Override
    public void onSuccess(List<TourPackageDomain> tourPackageDomainList) {
        List<TourPackageUI> tourPackageUIList = new ArrayList<>();
        for (TourPackageDomain tourPackageDomain : tourPackageDomainList) {
            TourPackageUI tourPackageUI = new TourPackageUI(
                    tourPackageDomain.getId(),
                    tourPackageDomain.getName(),
                    tourPackageDomain.getRating()
            );
            tourPackageUIList.add(tourPackageUI);
        }
        getTourPackageView().showTourPackages(tourPackageUIList);
    }

    @Override
    public void onFailure() {

    }
}
