package com.explore;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.explore.features.tour.presentation.TourFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_main_activity,new TourFragment())
                .commit();
    }

    // expose a method which allows any fragment to change
    // the app toolbar title
    public void setActivityToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
