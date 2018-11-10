package com.explore;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.explore.base.ExploreApplication;
import com.explore.base.ExploreDatabase;
import com.explore.features.login.LoginFragment;
import com.explore.features.user.data.UserDao;
import com.explore.features.user.domain.UserDomain;

public class SplashScreen extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                findLoggedInUser();
                // close this activity

            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void findLoggedInUser() {
        AsyncTask.execute(new Runnable() {
            final UserDao userDao = ExploreDatabase.getDatabase(getApplicationContext()).userDao();

            @Override
            public void run() {
                UserDomain userDomain = userDao.findLoggedInUser();
                if (userDomain != null) {
                    ExploreApplication.setCurrentUser(userDomain);
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.main_fragment_container, new LoginFragment())
                            .commit();
                }
            }
        });
    }
}
