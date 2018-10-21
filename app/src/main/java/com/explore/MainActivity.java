package com.explore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.explore.features.tourpackage.presentation.TourPackageFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_root, new TourPackageFragment())
                .commit();
    }
}
