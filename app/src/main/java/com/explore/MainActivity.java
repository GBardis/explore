package com.explore;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.explore.features.tour.presentation.TourFragment;
import com.explore.features.tourpackage.presentation.TourPackageFragment;

public class MainActivity extends AppCompatActivity implements TourFragment.TourFragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_root,new TourPackageFragment())
                .commit();
    }

    // expose a method which allows any fragment to change
    // the app toolbar title
    public void setActivityToolbarTitle(String title) {
//        getSupportActionBar().setTitle(title);
    }

    @Override
    public void transitionToTourFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_activity_root,new TourFragment())
                .commit();
    }
}
