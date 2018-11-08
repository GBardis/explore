package com.explore.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.explore.features.tour.data.TourDao;
import com.explore.features.tour.domain.TourDomain;
import com.explore.features.tourpackage.data.TourPackageDao;
import com.explore.features.tourpackage.domain.TourPackageDomain;
import com.explore.features.user.data.UserDao;
import com.explore.features.user.domain.UserDomain;

@Database(entities = {UserDomain.class, TourPackageDomain.class,TourDomain.class}, version = 1, exportSchema = false)
abstract public class ExploreDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract TourPackageDao tourPackageDao();

    public abstract TourDao tourDao();

    static private ExploreDatabase INSTANCE;

    public static ExploreDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ExploreDatabase.class, "explore_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
