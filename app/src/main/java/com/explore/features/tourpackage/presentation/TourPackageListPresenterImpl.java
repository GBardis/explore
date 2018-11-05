package com.explore.features.tourpackage.presentation;

import android.content.Context;

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

public class TourPackageListPresenterImpl implements TourPackagePresenter, TourPackageIteractor.OnTourPackageListFinishListener {
    @Getter
    @Setter
    TourPackageView tourPackageView;
    @Getter
    @Setter
    TourPackageIteractor tourPackageIteractor;

    public TourPackageListPresenterImpl(TourPackageView tourPackageView) {
        this.tourPackageView = tourPackageView;
        this.tourPackageIteractor = new TourPackageIteractorImpl();
    }

    @Override
    public void getTourPackages(Context context) {
        getTourPackageIteractor().getTourPackages(this, context);
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
        getTourPackageView().showTourPackages(tourPackageUIList);
    }

    @Override
    public void onFailure() {

    }
}
