package gcarroll.com.irishroadwatchlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import gcarroll.com.irishroadwatchlive.fragments.MyMapFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String NATIONAL_SUMMARY = "National Summary";

    private final String LOG_TAG = MyMapFragment.class.getSimpleName();

    NavigationView navigationView = null;

    Toolbar toolbar = null;

    Fragment fragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            final MyMapFragment mapFragment = new MyMapFragment();
            changeFragment(mapFragment);
//            final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
//                    .beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, mapFragment);
//            fragmentTransaction.commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {

        final int id = item.getItemId();

        switch (id) {
            case R.id.action_main:
                fragment = new MyMapFragment();
                changeFragment(fragment);
                break;
            case R.id.national_sum:
                Log.v(LOG_TAG, "National Summary Pressed");
                break;
            case R.id.dublin_cam:
                startIntent(DublinCamActivity.class);
                break;
            case R.id.settings:
                Log.v(LOG_TAG, "Settings Pressed");
                break;
        }

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startIntent(final Class<?> activityClass) {
        final Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void changeFragment(final Fragment fragment) {
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}
