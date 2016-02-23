package gcarroll.com.irishroadwatchlive.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import gcarroll.com.irishroadwatchlive.R;

/**
 * Created by gcarroll on 23/02/2016.
 */
public class MapPopupAdapter implements GoogleMap.InfoWindowAdapter {
  private View popup = null;

  private LayoutInflater inflater = null;

  public MapPopupAdapter(final LayoutInflater inflater) {
    this.inflater = inflater;
  }

  @Override
  public View getInfoWindow(final Marker marker) {
    if (popup == null) {
      popup = inflater.inflate(R.layout.map_info_window, null);
    }

    TextView tv = (TextView) popup.findViewById(R.id.incident_title);
    tv.setText(marker.getTitle());

    tv = (TextView) popup.findViewById(R.id.incident_snippet);
    tv.setText(marker.getSnippet());

    return (popup);

  }

  @SuppressLint("InflateParams")
  @Override
  public View getInfoContents(final Marker marker) {
    return (null);
  }
}
