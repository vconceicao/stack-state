package org.wtkf.challenge;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestGsonComponent {

  public static void main(String[] args) throws FileNotFoundException {
    final Component component = new Component("app", StateEnum.NO_DATA, StateEnum.NO_DATA);

    component.getCheckState().put("CPU_LOAD", StateEnum.NO_DATA);
    component.getCheckState().put("RAM_USAGE", StateEnum.NO_DATA);

    final String componentJson = new Gson().toJson(component);
    System.out.println(componentJson);

    //deserializing

    final FileReader fileReader = new FileReader("components.json");
    final Component componentFromJSON = new Gson().fromJson(fileReader, Component.class);

    System.out.println(componentFromJSON);


  }
}
