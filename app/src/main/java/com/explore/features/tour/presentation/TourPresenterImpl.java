package com.explore.features.tour.presentation;

import android.content.Context;

import com.explore.base.PresenterObserver;
import com.explore.features.reviewnew.data.ReviewInteractorImpl;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.data.TourObservable;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;
import com.explore.features.tourpackage.data.TourPackageInteractorImpl;
import com.explore.features.tourpackage.data.TourPackageObservable;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;

import java.util.ArrayList;
import java.util.List;

public class TourPresenterImpl extends PresenterObserver implements TourPresenter {

    private Context context;
    private TourView mTourView;
    private TourInteractor mTourInteractor;
    private ReviewInteractor mReviewInteractor;
    private TourPackageInteractor mTourPackageInteractor;
    private String mTourPackageId;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(Context context, TourView tourView) {

        this.mTourView = tourView;
        mTourInteractor = new TourInteractorImpl();
        mReviewInteractor = new ReviewInteractorImpl();
        mTourPackageInteractor = new TourPackageInteractorImpl();
        this.context = context;

    }

    @Override
    public void getTourPackage(Context context, String tourPackageId) {
        this.mTourPackageId = tourPackageId;
        mTourPackageInteractor.getTourPackages(this, context);
    }

    @Override
    public void getTourList(Context context, String tourPackageId) {
        mTourInteractor.getTourList(this, context, tourPackageId);
    }

    @Override
    public void getTourPackageReviews(String tourPackageId) {
//        mReviewInteractor.getReviewList(this, tourPackageId);
    }

    // TODO: implement observer pattern for review list
//    @Override
//    public void onReviewListSuccess(ArrayList<ReviewDomain> reviewDomainArrayList) {
//        ArrayList<ReviewUI> reviewUIArrayList = new ArrayList<>();
//
//        for (ReviewDomain reviewDomain : reviewDomainArrayList) {
//            reviewUIArrayList.add(
//                    new ReviewUI(
//                            reviewDomain.getId(),
//                            reviewDomain.getReviewTitle(),
//                            reviewDomain.getReviewScore(),
//                            reviewDomain.getReviewText()));
//        }
//
//        mTourView.showTourPackageReviewList(reviewUIArrayList);
//    }


    @Override
    public void updateTourPackageList(TourPackageObservable tourPackageObservable, Object o) {
        TourPackageDomain tourPackageDomain = null;
        // search for the correct Tour Package
        for (TourPackageDomain tpDomain : (List<TourPackageDomain>) o) {
            if (tpDomain.getId().equals(mTourPackageId)) {
                tourPackageDomain = tpDomain;
            }
        }

        mTourView.showTourPackage(
                new TourPackageUI(tourPackageDomain.getName(),
                        tourPackageDomain.getRegion(),
                        tourPackageDomain.getRating()));
    }


    @Override
    public void updateToursList(TourObservable tourObservable, Object o) {
        ArrayList<TourUI> tourUIArrayList = new ArrayList<>();

        for (TourDomain tourDomain : (List<TourDomain>) o) {
            tourUIArrayList.add(new TourUI(
                    tourDomain.getId(),
                    tourDomain.getTitle(),
                    tourDomain.getDescription(),
                    tourDomain.getPrice(),
                    tourDomain.getDuration(),
                    tourDomain.getBullets(),
                    tourDomain.getKeywords()
            ));
        }

        mTourView.showTourList(tourUIArrayList);
    }
}
