package com.explore.features.tour.data;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public class TourObservable {
    ArrayList<TourObserver> tourObserverList = new ArrayList<TourObserver>();

    boolean changeFlag = false;

    public void notifyObservers(final Object o) {
        if (hasChanged()) {
            for (final TourObserver mo : tourObserverList) {
                final TourObservable tourObservable = this;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mo.updateToursList(tourObservable, o);
                    }
                });
            }
            clearChanged();
        }
    }


    public void addObserver(TourObserver o) {
        tourObserverList.add(o);
    }

    public void removeObserver(TourObserver o) {
        tourObserverList.remove(o);
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
