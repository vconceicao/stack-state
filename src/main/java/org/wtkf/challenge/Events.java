package org.wtkf.challenge;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Events {

  @SerializedName("events")
  private final List<Alert> alerts;


  public Events(List<Alert> alerts) {
    this.alerts = alerts;
  }

  public List<Alert> getAlerts() {
    return alerts;
  }

  @Override
  public String toString() {
    return "Events{" +
        "alerts=" + alerts +
        '}';
  }
}
