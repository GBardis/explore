package com.explore.features.tourpackage.data;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public class TourPackageObservable {
    private ArrayList<TourPackageObserver> tourPackageObserverList = new ArrayList<TourPackageObserver>();

    private boolean changeFlag = false;

    public void notifyObservers(final Object o) {
        if (hasChanged()) {
            for (final TourPackageObserver mo : tourPackageObserverList) {
                final TourPackageObservable tourPackageObservable = this;
                // run on UI thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mo.updateTourPackageList(tourPackageObservable, o);
                    }
                });
            }
            clearChanged();
        }
    }


    public void addObserver(TourPackageObserver o) {
        tourPackageObserverList.add(o);
    }

    public void removeObserver(TourPackageObserver o) {
        tourPackageObserverList.remove(o);
    }

    public void setChanged() {
        changeFlag = true;
    }

    public boolean hasChanged() {
        return changeFlag;
    }

    protected void clearChanged() {
        changeFlag = false;
    }
}
