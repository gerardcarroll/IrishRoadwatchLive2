package gcarroll.com.irishroadwatchlive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import gcarroll.com.irishroadwatchlive.adapters.ViewPagerAdapter;
import gcarroll.com.irishroadwatchlive.fragments.TabMotorwayFragment;
import gcarroll.com.irishroadwatchlive.models.DublinCamera;
import gcarroll.com.irishroadwatchlive.requests.GsonRequest;
import gcarroll.com.irishroadwatchlive.requests.MyRequestQueue;

public class DublinCamActivity extends AppCompatActivity {

  private final String LOG_TAG = DublinCamActivity.class.getSimpleName();

  private Toolbar toolbar = null;

  private TabLayout tabLayout;

  private ViewPager viewPager;

  private ViewPagerAdapter viewPagerAdapter;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dublin_cam);

    // Assigning view variables to their respective view in xml by findViewByID method
    toolbar = (Toolbar) findViewById(R.id.dub_cam_toolbar);
    tabLayout = (TabLayout) findViewById(R.id.tabs);
    viewPager = (ViewPager) findViewById(R.id.viewpager);

    // Creating Adapter and setting that adapter to the setSupportActionBar method takes the toolbar and sets it as the
    // default action bar thus making the toolbar work like a normal action bar.
    viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(viewPagerAdapter);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // TabLayout.newTab() method creates a tab view, a Tab view is not the view which is below the tabs, its the tab
    // itself.
    final TabLayout.Tab motorwayTab = tabLayout.newTab();
    motorwayTab.setText("Motorway");
    final TabLayout.Tab northCityTab = tabLayout.newTab();
    northCityTab.setText("North City");
    final TabLayout.Tab southCityTab = tabLayout.newTab();
    southCityTab.setText("South City");

    // Adding the tab view to our tablayout at appropriate positions. As I want home at first position I am passing home
    // and 0 as argument to the tablayout and like wise for other tabs as well
    tabLayout.addTab(motorwayTab, 0);
    tabLayout.addTab(northCityTab, 1);
    tabLayout.addTab(southCityTab, 2);

    // TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes tab change colors in
    // different situations such as selected, active, inactive etc TabIndicatorColor sets the color for the
    // indicator below the tabs
    tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
    tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.tab_Indicator));

    // Adding an onPageChangeListener to the viewPager.
    // First we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
    // changes when a viewpager page changes.
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    downloadCamInfo();
  }

  private void downloadCamInfo() {
    final GsonRequest gsonRequest = new GsonRequest("http://selectunes.eu/api/trafficcam",
        new TypeToken<List<DublinCamera>>() {}.getType(), null, successDubCamListener(), errorDubCamListener());

    MyRequestQueue.getInstance(this).addToRequestQueue(gsonRequest);
  }

  private Response.Listener successDubCamListener() {
    return new Response.Listener<List<DublinCamera>>() {
      @Override
      public void onResponse(final List<DublinCamera> cameras) {
        // Do something with response
        final Map<String, List<DublinCamera>> map = new HashMap<>();
        for (final DublinCamera cam : cameras) {
          final String key = cam.getArea();
          if (map.get(key) == null) {
            map.put(key, new ArrayList<DublinCamera>());
          }
          map.get(key).add(cam);
        }
        final List<DublinCamera> motorwayCams = map.get("Motorway");
        final List<DublinCamera> northCams = map.get("North City");
        final List<DublinCamera> southCams = map.get("South City");

        Log.v(LOG_TAG, "Camera Junction: " + motorwayCams.get(0).getJunction());
        Log.v(LOG_TAG, "Camera Junction: " + northCams.get(0).getJunction());
        Log.v(LOG_TAG, "Camera Junction: " + southCams.get(0).getJunction());

        new TabMotorwayFragment(motorwayCams);
      }
    };
  }

  private Response.ErrorListener errorDubCamListener() {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(final VolleyError error) {
        Log.v(LOG_TAG, String.format("Response Error Received: %s", error.getMessage()));
      }
    };
  }
}
