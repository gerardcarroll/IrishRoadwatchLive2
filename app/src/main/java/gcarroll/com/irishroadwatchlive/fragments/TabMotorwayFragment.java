package gcarroll.com.irishroadwatchlive.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gcarroll.com.irishroadwatchlive.R;
import gcarroll.com.irishroadwatchlive.adapters.MotorwayCamAdapter;
import gcarroll.com.irishroadwatchlive.models.DublinCamera;

/**
 * Created by gcarroll on 24-02-2016.
 */

public class TabMotorwayFragment extends Fragment {

  public ArrayList<DublinCamera> mMotorwayCams;

  public RecyclerView mRecyclerView;

  public TabMotorwayFragment() {}

  public static TabMotorwayFragment getInstance(ArrayList<DublinCamera> motorwayCams) {
    TabMotorwayFragment tabMotorwayFragment = new TabMotorwayFragment();

    Bundle args = new Bundle();
    args.putParcelableArrayList("Motorway Cams", motorwayCams);
    tabMotorwayFragment.setArguments(args);
    return tabMotorwayFragment;
  }

  @Override
  public void setArguments(Bundle args) {
    super.setArguments(args);
    this.mMotorwayCams = args.getParcelableArrayList("Motorway Cams");
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      return null;
    }
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.pager_motorway_camera, container, false);

    mRecyclerView = (RecyclerView) view.findViewById(R.id.motorwayTabRecyclerView);

    // use this setting to improve performance if you know that changes in content do not change the layout size of the
    // RecyclerView
    mRecyclerView.setHasFixedSize(true);
    // use a linear layout manager
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    MotorwayCamAdapter adapter = new MotorwayCamAdapter(mMotorwayCams);
    mRecyclerView.setAdapter(adapter);

    return view;
  }

  @Override
  public void onResume() {
    // TODO Auto-generated method stub
    super.onResume();
  }

}
