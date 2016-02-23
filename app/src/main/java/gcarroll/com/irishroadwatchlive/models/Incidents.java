package gcarroll.com.irishroadwatchlive.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerard on 22/02/2016.
 */
public class Incidents {

  private ArrayList<Incident> incidents;

  public List<Incident> getIncidents() {
    return this.incidents;
  }

  public void setIncidents(ArrayList<Incident> incidents) {
    this.incidents = incidents;
  }
}
