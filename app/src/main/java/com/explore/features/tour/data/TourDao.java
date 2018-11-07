package com.explore.features.tour.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tourpackage.domain.TourPackageDomain;

import java.util.List;

@Dao
public abstract class TourDao {
    @Insert
    abstract void insertTours(List<TourDomain> tourDomainList);

    @Query("SELECT * FROM tours")
    abstract List<TourDomain> getTours();
}
