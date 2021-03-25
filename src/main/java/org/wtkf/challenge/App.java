package org.wtkf.challenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        //File graphJson  =  args[0]
        final FileReader file1 = new FileReader(args[0]);
        //File eventsJson  =  args[1]
        final FileReader file2 = new FileReader(args[1]);

        final Gson gson = new Gson();
        //Graph graph = JsonUtils.fromJson(graphJson)
        final Panel panel = gson.fromJson(file1, Panel.class);
        //List<Events> events = JsonUtils.fromJson(eventsJson)
        final Events events = gson.fromJson(file2, Events.class);

        final List<Alert> alerts = events.getAlerts();
        //events.stream.sort(e -> e.getTimeStamp())
        alerts.stream().sorted(Comparator.comparing(e -> e.getTimestamp()));

        final Map<String, Component> componentMap = panel.getGraph().getComponents().stream().collect(Collectors.toMap(c -> c.getId(), c -> c));

        //for (Event event: events){
        for(Alert alert: alerts){
            //Component c = graph.components().get(event.getComponent)
            final Component component = componentMap.get(alert.getComponent());
            //c.getCheckStates().get(event.getCheckState()).set(event.getState)
            component.getCheckState().put(alert.getCheckState(), alert.getState());
            //if(c.getOwnState() < event.getState)
            if(component.getOwnState().value < alert.getState().value) {
                //c.setOwnState(event.getState())
                component.setOwnState(alert.getState());
            }

            //if c.getOwnState()< StateEnum.CLEAR_STATE && c.getOwnState()>c.getDerivedState()
            if (component.getOwnState().value > StateEnum.CLEAR.value && component.getOwnState().value> component.getDerivedState().value) {
                //c.setDerivedState(c.getOwnState9))
                component.setDerivedState(component.getOwnState());
            } else if ( component.getDerivedState().value < StateEnum.WARNING.value) {
                //c.setDerivedState(c.getOwnState9))
                component.setDerivedState(StateEnum.NO_DATA);
            }
            //propagate derived state

            //if c.getDerivedState() > StateEnum.CLEAR_STATE
            if(component.getDerivedState().value > StateEnum.CLEAR.value) {
                //for (String componentName: c.getDependencies())
                if(component.getDependecyOf() != null){
                    for (String componentName : component.getDependecyOf()) {
                        //dependecyComponent = graph.getComponents().get(componentName)
                        final Component dependecyComponent = componentMap.get(componentName);
                        //if dependecyComponent.getDerivedState() < c.getDerivedState()
                        if (dependecyComponent.getDerivedState().value < component.getDerivedState().value) {
                            //dependencyComponent.setDerivedState(c.getDerivedState()
                            dependecyComponent.setDerivedState(component.getDerivedState());
                        }

                    }
                }
            }
        }

        final Graph graph = new Graph(componentMap.values().stream().collect(Collectors.toList()));
        final Panel panel1 = new Panel(graph);
        Gson gsonB = new GsonBuilder().setPrettyPrinting().create();
        final String resultJson = gsonB.toJson(panel);
        System.out.println(resultJson);


    }
}
