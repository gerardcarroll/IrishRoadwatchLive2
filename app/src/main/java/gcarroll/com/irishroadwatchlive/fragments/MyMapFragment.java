package gcarroll.com.irishroadwatchlive.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import gcarroll.com.irishroadwatchlive.R;
import gcarroll.com.irishroadwatchlive.models.Incident;
import gcarroll.com.irishroadwatchlive.requests.GsonRequest;

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

  /**
   * Note that this may be null if the Google Play services APK is not available.
   */

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
    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(53.510138, -7.865643)));
    mMap.animateCamera(CameraUpdateFactory.zoomTo(6.7f));

    // TODO
    // mMap.setMyLocationEnabled(true);
    // For dropping a marker at a point on the Map
    mMap.addMarker(
      new MarkerOptions().position(new LatLng(54.266077, -8.453736)).title("My Home").snippet("Home Address"));
    // For zooming automatically to the Dropped PIN Location
    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(54.266077, -8.453736), 12.0f));

    final RequestQueue queue = Volley.newRequestQueue(getContext());
    final GsonRequest gsonRequest = new GsonRequest("http://selectunes.eu/api/test", Incident.class, null,
        successIncidentListener(), errorIncidentListener());
    queue.add(gsonRequest);
  }

  private Response.Listener<Incident> successIncidentListener() {
    return new Response.Listener<Incident>() {
      @Override
      public void onResponse(final Incident response) {
        // Do something with response
      }
    };
  }

  private Response.ErrorListener errorIncidentListener() {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(final VolleyError error) {
        // Do whatever you want to do with error.getMessage();
      }
    };
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
