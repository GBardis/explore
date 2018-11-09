package com.explore.features.reviewnew.observers;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public class ReviewObservable {
    ArrayList<ReviewObserver> reviewObserverList = new ArrayList<ReviewObserver>();

    boolean changeFlag = false;

    public void notifyObservers(final Object o) {
        if (hasChanged()) {
            for (final ReviewObserver mo : reviewObserverList) {
                final ReviewObservable reviewObservable = this;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mo.updateReviewsList(reviewObservable, o);
                        reviewObserverList.remove(mo);
                    }
                });
            }
            clearChanged();
        }
    }


    public void addObserver(ReviewObserver o) {
        reviewObserverList.add(o);
    }

    public void removeObserver(ReviewObserver o) {
        reviewObserverList.remove(o);
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
