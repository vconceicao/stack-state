package org.wtkf.challenge;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Graph {

  private List<Component> components;

  public Graph() {

  }


  public Graph(List<Component> components) {
    this.components = components;

  }

  public List<Component> getComponents() {
    return components;
  }


  @Override
  public String toString() {
    return "Graph{" +
        "components=" + components +
        '}';
  }

}
