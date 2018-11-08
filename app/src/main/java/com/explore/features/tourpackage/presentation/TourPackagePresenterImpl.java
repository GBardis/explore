package com.explore.features.tourpackage.presentation;

import android.content.Context;

import com.explore.base.PresenterObserver;
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

public class TourPackagePresenterImpl extends PresenterObserver implements TourPackagePresenter {
    @Getter
    @Setter
    TourPackageView tourPackageView;
    @Getter
    @Setter
    TourPackageInteractor tourPackageIteractor;

    private List<TourPackageUI> tourPackageUIList;

    public TourPackagePresenterImpl(TourPackageView tourPackageView) {
        this.tourPackageView = tourPackageView;
        this.tourPackageIteractor = new TourPackageInteractorImpl();
    }

    @Override
    public void getTourPackages(Context context) {
        getTourPackageIteractor().getTourPackages(this, context);
    }


    @Override
    public void updateTourPackageList(TourPackageObservable tourPackageObservable, final Object o) {

        tourPackageUIList = new ArrayList<>();
        for (TourPackageDomain tourPackageDomain : (List<TourPackageDomain>) o) {
            TourPackageUI tourPackageUI = new TourPackageUI(
                    tourPackageDomain.getId(),
                    tourPackageDomain.getName(),
                    tourPackageDomain.getRegion(),
                    tourPackageDomain.getRating(),
                    tourPackageDomain.getPlaceId()
            );
            tourPackageUIList.add(tourPackageUI);
        }
        getTourPackageView().showTourPackages(tourPackageUIList);

    }
}
