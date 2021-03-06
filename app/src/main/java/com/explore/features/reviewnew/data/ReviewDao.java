package com.explore.features.reviewnew.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.explore.features.reviewnew.domain.ReviewDomain;

import java.util.List;

@Dao
public abstract class ReviewDao {
    @Insert
    abstract void insertReviews(List<ReviewDomain> reviewDomainList);

    @Query("SELECT * FROM reviews WHERE tourPackageId=:tourPackageId")
    abstract List<ReviewDomain> getReviews(final String tourPackageId);

    @Query("DELETE FROM reviews")
    abstract void deleteAllReviews();

    @Transaction
    void updateReviewsDb(List<ReviewDomain> reviewDomainList) {
        deleteAllReviews();
        insertReviews(reviewDomainList);
    }
}
