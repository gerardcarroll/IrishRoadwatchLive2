package gcarroll.com.irishroadwatchlive.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.reflect.TypeToken;

import gcarroll.com.irishroadwatchlive.R;
import gcarroll.com.irishroadwatchlive.adapters.MapPopupAdapter;
import gcarroll.com.irishroadwatchlive.models.Incident;
import gcarroll.com.irishroadwatchlive.requests.GsonRequest;
import gcarroll.com.irishroadwatchlive.requests.MyRequestQueue;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {!-@link
 * MyMapFragment.OnFragmentInteractionListener} interface to handle interaction events. Use the {!-@link
 * MyMapFragment#newInstance} factory method to create an instance of this fragment.
 */
public class MyMapFragment extends Fragment implements OnMapReadyCallback {

  private static View view;

  private final String LOG_TAG = MyMapFragment.class.getSimpleName();

  private GoogleMap mMap;

  // private OnFragmentInteractionListener mListener;
  private GoogleApiClient mGoogleApiClient;

  public MyMapFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (container == null) {
      return null;
    }

    // Get the view
    view = inflater.inflate(R.layout.fragment_map, container, false);

    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.my_map);
    mapFragment.getMapAsync(this);

    // Inflate the layout for this fragment
    return view;
  }

  /**
   * Manipulates the map once available. This callback is triggered when the map is ready to be used. This is where we
   * can add markers or lines, add listeners or move the camera. If Google Play services is not installed on the device,
   * the user will be prompted to install it inside the SupportMapFragment. This method will only be triggered once the
   * user has installed Google Play services and returned to the app.
   */
  @Override
  public void onMapReady(final GoogleMap googleMap) {
    mMap = googleMap;

    // Setup map features and starting position and zoom level
    mMap.setTrafficEnabled(false);
    // For zooming automatically to a Location
    // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(53.510138, -7.865643), 6.7f));
    // mMap.animateCamera(CameraUpdateFactory.zoomTo(6.7f));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(53.510138, -7.865643)));
    mMap.animateCamera(CameraUpdateFactory.zoomTo(6.7f));

    // TODO
    // mMap.setMyLocationEnabled(true);
    // TODO
    // Drop a marker at user's location on the Map
    mMap.addMarker(new MarkerOptions().position(new LatLng(54.266077, -8.453736)).title("You are here!"));

    mMap.setInfoWindowAdapter(new MapPopupAdapter(getActivity().getLayoutInflater()));

    final RequestQueue queue = Volley.newRequestQueue(getContext());

    final GsonRequest gsonRequest = new GsonRequest<>("http://selectunes.eu/api/test",
        new TypeToken<List<Incident>>() {}.getType(), null, successIncidentListener(), errorIncidentListener());

    MyRequestQueue.getInstance(getContext()).addToRequestQueue(gsonRequest);
  }

  private Response.Listener successIncidentListener() {
    return new Response.Listener<List<Incident>>() {
      @Override
      public void onResponse(final List<Incident> incidents) {
        // Do something with response
        for (final Incident incident : incidents) {
          incident.setMapIcon(incident.getIncidentTypeID());
          incident.setDate(incident.getUpdatedAt());
          createMapMarker(incident);
        }
      }
    };
  }

  private Response.ErrorListener errorIncidentListener() {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(final VolleyError error) {
        Log.v(LOG_TAG, String.format("Response Error Received: %s", error.getMessage()));
      }
    };
  }

  private void createMapMarker(final Incident incident) {
    mMap.addMarker(new MarkerOptions().anchor(0, 1).title(incident.getTitle()).snippet(incident.getDate())
        .icon(incident.getMapIcon()).position(new LatLng(incident.getLatitude(), incident.getLongitude()))
        .title(incident.getReport()));
  }

  // @Override
  // public void onAttach(Context context) {
  // super.onAttach(context);
  // if (context instanceof OnFragmentInteractionListener) {
  // mListener = (OnFragmentInteractionListener) context;
  // } else {
  // throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
  // }
  // }

  // @Override
  // public void onDetach() {
  // super.onDetach();
  // mListener = null;
  // }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an interaction in this
   * fragment to be communicated to the activity and potentially other fragments contained in that activity.
   * <p/>
   * See the Android Training lesson
   * <a href= "http://developer.android.com/training/basics/fragments/communicating.html" >Communicating with Other
   * Fragments</a> for more information.
   */
  // public interface OnFragmentInteractionListener {
  // // TODO: Update argument type and name
  // void onFragmentInteraction(Uri uri);
  // }
}
