package gcarroll.com.irishroadwatchlive.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import gcarroll.com.irishroadwatchlive.R;
import gcarroll.com.irishroadwatchlive.adapters.ViewPagerAdapter;
import gcarroll.com.irishroadwatchlive.sliding_tab_layout.SlidingTabLayout;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {@link DublinCamFragment
 * .OnFragmentInteractionListener} interface to handle interaction events. Use the
 */
public class DublinCamFragment extends AppCompatActivity {

  ViewPager pager;

  ViewPagerAdapter adapter;

  SlidingTabLayout tabs;

  CharSequence Titles[] = { "Home", "Events" };

  Toolbar toolbar;

  int Numboftabs = 2;

  // private OnFragmentInteractionListener mListener;

  public DublinCamFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dublin_cam);

    // Creating The Toolbar and setting it as the Toolbar for the activity

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
    adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

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

  }

  // @Override
  // public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
  // {
  //
  // if (container == null) {
  // return null;
  // }
  //
  // // Inflate the layout for this fragment
  // return inflater.inflate(R.layout.fragment_dublin_cam, container, false);
  // }

  // @Override
  // public void onAttach(final Context context) {
  // super.onAttach(context);
  // if (context instanceof OnFragmentInteractionListener) {
  // mListener = (OnFragmentInteractionListener) context;
  // }
  // else {
  // throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
  // }
  // }
  //
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
