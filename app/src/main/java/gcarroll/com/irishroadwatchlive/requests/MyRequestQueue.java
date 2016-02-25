package gcarroll.com.irishroadwatchlive.requests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by gcarroll on 25/02/2016.
 */
public class MyRequestQueue {
  private static MyRequestQueue mInstance;

  private static Context mCtx;

  private RequestQueue mRequestQueue;

  private MyRequestQueue(final Context context) {
    mCtx = context;
    mRequestQueue = getRequestQueue();
  }

  public static synchronized MyRequestQueue getInstance(final Context context) {
    if (mInstance == null) {
      mInstance = new MyRequestQueue(context);
    }
    return mInstance;
  }

  private RequestQueue getRequestQueue() {
    if (mRequestQueue == null) {
      // getApplicationContext() is key, it keeps you from leaking the
      // Activity or BroadcastReceiver if someone passes one in.
      mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
    }
    return mRequestQueue;
  }

  public <T> void addToRequestQueue(final Request<T> req) {
    getRequestQueue().add(req);
  }
}
