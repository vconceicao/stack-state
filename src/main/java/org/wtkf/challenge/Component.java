package org.wtkf.challenge;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.plaf.nimbus.State;

public class Component {

  private String id;
  @SerializedName("own_state")
  private StateEnum ownState;
  @SerializedName("derived_state")
  private StateEnum derivedState;
  @SerializedName("check_states")
  private Map<String, StateEnum> checkState = new HashMap<>();
  @SerializedName("depends_on")
  private Set<String> dependsOn ;
  @SerializedName("dependency_of")
  private Set<String> dependecyOf;

  public Component() {

  }

  public Component(String id, StateEnum ownState, StateEnum derivedState) {
    this.id = id;
    this.ownState = ownState;
    this.derivedState = derivedState;
  }

  public String getId() {
    return id;
  }

  public StateEnum getOwnState() {
    return ownState;
  }

  public StateEnum getDerivedState() {
    return derivedState;
  }

  public Map<String, StateEnum> getCheckState() {
    return checkState;
  }

  public Set<String> getDependsOn() {
    return dependsOn;
  }

  public Set<String> getDependecyOf() {
    return dependecyOf;
  }

  @Override
  public String toString() {
    return "Component{" +
        "id='" + id + '\'' +
        ", ownState='" + ownState + '\'' +
        ", derivedState='" + derivedState + '\'' +
        ", checkState=" + checkState +
        ", dependsOn=" + dependsOn +
        ", dependecyOf=" + dependecyOf +
        '}';
  }

  public void setOwnState(StateEnum state) {
    this.ownState = state;
  }

  public void setDerivedState(StateEnum derivedState) {
    this.derivedState = derivedState;
  }
}
