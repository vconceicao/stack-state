package org.wtkf.challenge;

public class Panel {


  private Graph graph;

  public Panel() {
  }

  public Panel(Graph graph) {
    this.graph = graph;
  }

  public Graph getGraph() {
    return graph;
  }

  @Override
  public String toString() {
    return "Panel{" +
        "graph=" + graph +
        '}';
  }
}

