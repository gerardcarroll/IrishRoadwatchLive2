package gcarroll.com.irishroadwatchlive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import gcarroll.com.irishroadwatchlive.models.DublinCamera;
import gcarroll.com.irishroadwatchlive.requests.GsonRequest;

public class DublinCamActivity extends AppCompatActivity {

  private final String LOG_TAG = DublinCamActivity.class.getSimpleName();

  Toolbar toolbar = null;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dublin_cam);

    toolbar = (Toolbar) findViewById(R.id.dub_cam_toolbar);
    setSupportActionBar(toolbar);

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
        final Map<String, List<DublinCamera>> map = new HashMap<String, List<DublinCamera>>();
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
