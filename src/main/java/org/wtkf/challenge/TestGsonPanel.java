package org.wtkf.challenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TestGsonPanel {
  public static void main(String[] args) throws FileNotFoundException {

    final Component component = new Component("app", StateEnum.NO_DATA, StateEnum.NO_DATA);

    component.getCheckState().put("CPU_LOAD", StateEnum.NO_DATA);
    component.getCheckState().put("RAM_USAGE", StateEnum.NO_DATA);

    final String componentJson = new Gson().toJson(component);
    System.out.println(componentJson);

    final Component component2  = new Component("app", StateEnum.NO_DATA, StateEnum.NO_DATA);

    component2 .getCheckState().put("CPU_LOAD", StateEnum.NO_DATA);
    component2 .getCheckState().put("RAM_USAGE", StateEnum.NO_DATA);

    final List<Component> compo = List.of(component, component2);

    final Graph graph = new Graph(compo);

    final Panel panel = new Panel(graph);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    final String graphJSON = gson.toJson(panel);

    //System.out.println(graphJSON);

    //deserializing

    final FileReader fileReader = new FileReader("sample-initial.json");

    final Panel panel1 = new Gson().fromJson(fileReader, Panel.class);

    System.out.println(panel1);

    final String toJson = gson.toJson(panel1);
    System.out.println("Serializing");
    System.out.println(toJson);


  }
}
