package com.example.gupta.ruralcommunication.HomeActivity.View;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gupta.ruralcommunication.AreaInfoFragment.View.AreaInfoFragment;
import com.example.gupta.ruralcommunication.DevelopmentFragment.View.DevelopmentStatsFragment;
import com.example.gupta.ruralcommunication.LanguageFragment.View.LanguageFragment;
import com.example.gupta.ruralcommunication.ProblemFragment.View.ProblemReportingFragment;
import com.example.gupta.ruralcommunication.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        titleTextView=findViewById(R.id.title_text_view);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_container,new AreaInfoFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        if (id == R.id.nav_about) {
            titleTextView.setText("About the Area");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_container,new AreaInfoFragment())
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_prob) {
            titleTextView.setText("Issues the Area faces");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_container,new ProblemReportingFragment())
                    .commit();

        } else if (id == R.id.nav_dev) {
            titleTextView.setText("Progress In Time");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_container,new DevelopmentStatsFragment())
                    .commit();

        } else if (id == R.id.nav_lang) {
            titleTextView.setText("Communication Util");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_container,new LanguageFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
