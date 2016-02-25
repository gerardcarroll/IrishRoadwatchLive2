package gcarroll.com.irishroadwatchlive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import gcarroll.com.irishroadwatchlive.adapters.ViewPagerAdapter;
import gcarroll.com.irishroadwatchlive.models.DublinCamera;
import gcarroll.com.irishroadwatchlive.requests.GsonRequest;
import gcarroll.com.irishroadwatchlive.sliding_tab_layout.SlidingTabLayout;

public class DublinCamActivity extends AppCompatActivity {

  private final String LOG_TAG = DublinCamActivity.class.getSimpleName();

  ViewPager pager;

  ViewPagerAdapter adapter;

  SlidingTabLayout tabs;

  CharSequence Titles[] = { "Motorway", "North City", "South City" };

  int NumbOfTabs = 3;

  Toolbar toolbar = null;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dublin_cam);

    toolbar = (Toolbar) findViewById(R.id.dub_cam_toolbar);
    setSupportActionBar(toolbar);

    // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
    adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, NumbOfTabs);

    // Assigning ViewPager View and setting the adapter
    pager = (ViewPager) findViewById(R.id.viewpager);
    pager.setAdapter(adapter);

    // Assiging the Sliding Tab Layout View
    tabs = (SlidingTabLayout) findViewById(R.id.tabs);
    tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in
    // Available width

    // Setting Custom Color for the Scroll bar indicator of the Tab View
    tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
      @Override
      public int getIndicatorColor(int position) {
        return ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
      }
    });

    // Setting the ViewPager For the SlidingTabsLayout
    tabs.setViewPager(pager);

    downloadCamInfo();
  }

  private void downloadCamInfo() {
    final RequestQueue queue = Volley.newRequestQueue(this);

    final GsonRequest gsonRequest = new GsonRequest("http://selectunes.eu/api/trafficcam",
        new TypeToken<List<DublinCamera>>() {}.getType(), null, successDubCamListener(), errorDubCamListener());
    queue.add(gsonRequest);
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

        final List<DublinCamera> motorWayCams = map.get("Motorway");

        Log.v(LOG_TAG, "Camera Junction: " + motorWayCams.get(0).getJunction());

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
