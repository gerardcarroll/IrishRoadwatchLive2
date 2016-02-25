package gcarroll.com.irishroadwatchlive.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gcarroll.com.irishroadwatchlive.R;
import gcarroll.com.irishroadwatchlive.models.DublinCamera;

/**
 * Created by gcarroll on 24-02-2016.
 */

public class TabMotorwayFragment extends Fragment {

  List<DublinCamera> motorwayCams;

  public TabMotorwayFragment(final List<DublinCamera> motorwayCams) {
    this.motorwayCams = motorwayCams;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.pager_motorway_camera, container, false);

    final TextView textView = (TextView) view.findViewById(R.id.motorwayTabView);
    if (motorwayCams != null) {
      textView.setText(motorwayCams.get(0).getJunction());
    }
    return view;
  }

}
