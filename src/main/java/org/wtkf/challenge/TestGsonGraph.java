package org.wtkf.challenge;

import com.google.gson.Gson;
import java.util.List;

public class TestGsonGraph {

  public static void main(String[] args) {

    final Component component = new Component("app", StateEnum.NO_DATA, StateEnum.NO_DATA);

    component.getCheckState().put("CPU_LOAD", StateEnum.NO_DATA);
    component.getCheckState().put("RAM_USAGE", StateEnum.NO_DATA);

    final String componentJson = new Gson().toJson(component);
    System.out.println(componentJson);

    final Component component2  = new Component("db", StateEnum.NO_DATA, StateEnum.NO_DATA);

    component2 .getCheckState().put("CPU_LOAD", StateEnum.NO_DATA);
    component2 .getCheckState().put("RAM_USAGE", StateEnum.NO_DATA);

    final List<Component> compo = List.of(component, component2);

    final Graph graph = new Graph(compo);

    final String graphJSON = new Gson().toJson(graph);

    System.out.println(graphJSON);
  }
}
