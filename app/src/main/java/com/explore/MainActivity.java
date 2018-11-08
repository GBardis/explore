package com.explore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.explore.features.login.LoginFragment;
import com.explore.features.reviewnew.presentation.ReviewNewFragment;
import com.explore.features.tour.presentation.TourFragment;
import com.explore.features.tourpackage.presentation.TourPackageFragment;
import com.explore.features.user.presentation.UserFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        TourFragment.TourFragmentListener, ReviewNewFragment.ReviewNewFragmentListener,
        TourPackageFragment.TourPackageListener, UserFragment.UserFragmentListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        // Setup SupportActionBar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        // add image to nav drawer header
//        View header = navigationView.getHeaderView(0);
//        ImageView imageView = header.findViewById(R.id.nav_drawer_image);
//        Picasso.get().load("https://cache-graphicslib.viator.com/graphicslib/thumbs360x240/2916/SITours/city-sightseeing-barcelona-hop-on-hop-off-tour-in-barcelona-534067.jpg")
//                .resize(100, 100)
//                .centerCrop()
//                .into(imageView);
//

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, new LoginFragment())
                .commit();
    }

    public void setActivityToolbarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    public void transitionToTourFragment(Bundle bundle) {
        TourFragment tourFragment = new TourFragment();
        tourFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment_container, tourFragment)
                .commit();
    }

    @Override
    public void transitionToTourPackage() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment_container, new TourPackageFragment())
                .commit();
    }


    @Override
    public void transitionToUserFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment_container, new UserFragment())
                .commit();
    }

    @Override
    public void transitionToReviewNewFragment(Bundle bundle) {
        ReviewNewFragment reviewNewFragment = new ReviewNewFragment();
        reviewNewFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment_container, reviewNewFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile)
            transitionToUserFragment();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
