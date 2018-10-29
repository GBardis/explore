package com.explore.features.tour.presentation;

import com.explore.features.reviewnew.data.ReviewInteractorImpl;
import com.explore.features.reviewnew.domain.ReviewDomain;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageDomain;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

public class TourPresenterImpl implements TourPresenter, TourInteractor.OnTourPackageFinishListener, ReviewInteractor.OnReviewListFinishListener {

    private TourView mTourView;
    private TourInteractor mTourInteractor;
    private ReviewInteractor mReviewInteractor;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(TourView tourView) {

        this.mTourView = tourView;
        mTourInteractor = new TourInteractorImpl();
        mReviewInteractor = new ReviewInteractorImpl();
    }

    @Override
    public void getTourPackage(String tourPackageId) {
        mTourInteractor.getTourPackage(this, tourPackageId);
    }

    @Override
    public void getTourPackageReviews(String tourPackageId) {
        mReviewInteractor.getReviewList(this, tourPackageId);
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
                        tourPackageDomain.getRating(),
                        tourPackageDomain.getDescription()));
    }

    @Override
    public void onSuccess(ArrayList<ReviewDomain> reviewDomainArrayList) {
        ArrayList<ReviewUI> reviewUIArrayList = new ArrayList<>();

        for (ReviewDomain reviewDomain : reviewDomainArrayList) {
            reviewUIArrayList.add(
                    new ReviewUI(
                            reviewDomain.getId(),
                            reviewDomain.getReviewTitle(),
                            reviewDomain.getReviewScore(),
                            reviewDomain.getReviewText()));
        }

        mTourView.showTourPackageReviewList(reviewUIArrayList);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFailure() {

    }
}
