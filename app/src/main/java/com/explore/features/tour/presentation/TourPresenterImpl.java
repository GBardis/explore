package com.explore.features.tour.presentation;

import android.content.Context;

import com.explore.features.reviewnew.data.ReviewInteractorImpl;
import com.explore.features.reviewnew.domain.ReviewDomain;
import com.explore.features.reviewnew.domain.ReviewInteractor;
import com.explore.features.tour.data.TourInteractorImpl;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tour.domain.TourInteractor;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;
import com.explore.features.tourpackage.PresenterObserver;
import com.explore.features.tourpackage.data.TourPackageInteractorImpl;
import com.explore.features.tourpackage.data.TourPackageObservable;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.tourpackage.domain.TourPackageInteractor;

import java.util.ArrayList;
import java.util.List;

public class TourPresenterImpl extends PresenterObserver implements TourPresenter, TourInteractor.OnTourListFinishListener, ReviewInteractor.OnReviewListFinishListener, TourPackageInteractor.OnTourPackageFinishListener {

    private Context context;
    private TourView mTourView;
    private TourInteractor mTourInteractor;
    private ReviewInteractor mReviewInteractor;
    private TourPackageInteractor mTourPackageInteractor;
    private String mTourPackageId;

    public TourPresenterImpl() {
    }

    public TourPresenterImpl(Context context,TourView tourView) {

        this.mTourView = tourView;
        mTourInteractor = new TourInteractorImpl();
        mReviewInteractor = new ReviewInteractorImpl();
        mTourPackageInteractor = new TourPackageInteractorImpl();
        this.context = context;
    }

    public TourPresenterImpl(TourView tourView,String tourPackageId) {

        this.mTourView = tourView;
        mTourInteractor = new TourInteractorImpl();
        mReviewInteractor = new ReviewInteractorImpl();
        mTourPackageInteractor = new TourPackageInteractorImpl();
        mTourPackageId = tourPackageId;
    }

    @Override
    public void getTourPackage(Context context,String tourPackageId) {
        mTourPackageInteractor.getTourPackages(this, context);
    }

    @Override
    public void getTourList(Context context, String tourPackageId) {
        mTourInteractor.getTourList(this, tourPackageId);
    }

    @Override
    public void getTourPackageReviews(String tourPackageId) {
        mReviewInteractor.getReviewList(this, tourPackageId);
    }

    @Override
    public void onReviewListSuccess(ArrayList<ReviewDomain> reviewDomainArrayList) {
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
    public void onTourListSuccess(ArrayList<TourDomain> tourDomainArrayList) {
        ArrayList<TourUI> tourUIArrayList = new ArrayList<>();

        for (TourDomain tourDomain : tourDomainArrayList) {
            tourUIArrayList.add(new TourUI(tourDomain.getName(), tourDomain.getRating()));
        }

        mTourView.showTourList(tourUIArrayList);

    }

    @Override
    public void update(TourPackageObservable tourPackageObservable, Object o) {

        TourPackageDomain tourPackageDomain = null;

        // search for the correct Tour Package
        for(TourPackageDomain tpDomain : (List<TourPackageDomain>) o){
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
    public void onError() {

    }

    @Override
    public void onTourPackageSuccess(TourPackageDomain tourPackageDomain) {
        mTourView.showTourPackage(
                new TourPackageUI(tourPackageDomain.getName(),
                        tourPackageDomain.getRegion(),
                        tourPackageDomain.getRating()));
    }

    @Override
    public void onFailure() {

    }
}
