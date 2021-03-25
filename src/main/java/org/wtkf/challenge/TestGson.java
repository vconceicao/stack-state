package org.wtkf.challenge;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class TestGson {
  public static void main(String[] args) throws IOException {

    final Alert alert1 = new Alert("1", "db", "CPU_LOAD", StateEnum.NO_DATA);
    final Alert alert2 = new Alert("2", "db", "CPU_LOAD", StateEnum.NO_DATA);

    final List<Alert> list = new ArrayList<>();

    list.add(alert1);
    list.add(alert2);

    final Events events = new Events(list);

    final String json = new Gson().toJson(events, Events.class);

    final Events events1 = new Gson().fromJson(json, Events.class);

    //C:\dev\repo-stack-state\events.json
    Path path = Paths.get("events.json");

    final List<String> strings = Files.readAllLines(path);

    final FileReader fileReader = new FileReader("events.json");

    final Events events2 = new Gson().fromJson(fileReader, Events.class);


    //System.out.println(json);
    //System.out.println(events1);
    //System.out.println(strings);
    System.out.println(events2);
  }
}
