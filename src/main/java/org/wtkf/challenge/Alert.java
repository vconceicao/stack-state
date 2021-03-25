package org.wtkf.challenge;

import com.google.gson.annotations.SerializedName;

public class Alert {

  private String timestamp;
  private String component;
  @SerializedName("check_state")
  private String checkState;
  private StateEnum state;

  public Alert() {
  }

  public Alert(String timestamp, String component, String checkState, StateEnum state) {
    this.timestamp = timestamp;
    this.component = component;
    this.checkState = checkState;
    this.state = state;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getComponent() {
    return component;
  }

  public String getCheckState() {
    return checkState;
  }

  public StateEnum getState() {
    return state;
  }

  @Override
  public String toString() {
    return "Alert{" +
        "timestamp='" + timestamp + '\'' +
        ", component='" + component + '\'' +
        ", checkState='" + checkState + '\'' +
        ", state='" + state + '\'' +
        '}';
  }
}
