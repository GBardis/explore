package com.explore.features.tourpackage.presentation;

import com.explore.features.tourpackage.data.TourPackageIteractorImpl;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageIteractor;
import com.explore.features.tourpackage.domain.TourPackagePresenter;
import com.explore.features.tourpackage.domain.TourPackageUI;
import com.explore.features.tourpackage.domain.TourPackageView;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TourPackagePresenterImpl implements TourPackagePresenter, TourPackageIteractor.OnTourPackageFinishListener {
    @Getter
    @Setter
    TourPackageView tourPackageView;
    @Getter
    @Setter
    TourPackageIteractor tourPackageIteractor;

    public TourPackagePresenterImpl(TourPackageView tourPackageView) {
        this.setTourPackageView(tourPackageView);
        this.setTourPackageIteractor(new TourPackageIteractorImpl());
    }

    @Override
    public void getTourPackages() {
        tourPackageIteractor.getTourPackages(this);
    }

    @Override
    public void getFilteredTourPackages(String filter) {

    }

    @Override
    public void onSuccess(List<TourPackageDomain> tourPackageDomainList) {
        List<TourPackageUI> tourPackageUIList = new ArrayList<>();
        for (TourPackageDomain tourPackageDomain : tourPackageDomainList) {
            TourPackageUI tourPackageUI = new TourPackageUI(
                    tourPackageDomain.getName(),
                    tourPackageDomain.getRating()
            );
            tourPackageUIList.add(tourPackageUI);
        }
        tourPackageView.showTourPackages(tourPackageUIList);
    }

    @Override
    public void onFailure() {

    }
}
