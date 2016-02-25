package gcarroll.com.irishroadwatchlive.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gcarroll.com.irishroadwatchlive.R;

/**
 * Created by gcarroll on 24-02-2016.
 */

public class TabMotorwayFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.pager_motorway_camera, container, false);
  }

}
