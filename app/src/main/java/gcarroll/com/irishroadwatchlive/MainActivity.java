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

import butterknife.Bind;
import butterknife.ButterKnife;
import gcarroll.com.irishroadwatchlive.fragments.MyMapFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private final String LOG_TAG = MyMapFragment.class.getSimpleName();

  // Fragment fragment;

  @Bind(R.id.navigation_view)
  NavigationView navigationView;

  @Bind(R.id.drawer_layout)
  DrawerLayout drawerLayout;

  @Bind(R.id.toolbar)
  Toolbar toolbar;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    if (savedInstanceState == null) {
      final MyMapFragment mapFragment = new MyMapFragment();
      changeFragment(mapFragment);
    }

    setSupportActionBar(toolbar);

    // final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawerLayout.setDrawerListener(toggle);
    toggle.syncState();

    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public boolean onNavigationItemSelected(final MenuItem item) {

    final int id = item.getItemId();

    switch (id) {
      // case R.id.action_main:
      // fragment = new MyMapFragment();
      // changeFragment(fragment);
      // break;
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

    drawerLayout.closeDrawer(GravityCompat.START);
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
