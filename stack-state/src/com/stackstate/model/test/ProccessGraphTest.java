package com.stackstate.model.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import com.stackstate.model.AlertCheck;
import com.stackstate.model.Component;
import com.stackstate.model.Event;
import com.stackstate.model.Graph;
import com.stackstate.model.Panel;
import com.stackstate.model.ProccessGraph;
import com.stackstate.model.StateEnum;

public class ProccessGraphTest {

	@Test
	public void testProccessGraphWithOneEvent() {

		//creating components
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		
		app.dependsOn(Collections.singleton(db));
		db.dependsOn(Collections.singleton(app));
		
		app.calculateStates("CPU load", StateEnum.NO_DATA);
		db.calculateStates("CPU load", StateEnum.NO_DATA);
		
		Collection<Component> components = new HashSet<>();
		Collections.addAll(components, app, db);
		
		//creating a graph
		Graph graph = new Graph(components);
		
		
		Panel panel = new Panel(graph);
		
		
		//creating events
		Event event1 = new Event("db", 1, "CPU load", StateEnum.WARNING);
		Event event2 = new Event("app", 2, "CPU load", StateEnum.NO_DATA);
	
		Collection<Event> events = new HashSet<>();
		Collections.addAll(events, event1, event2);
		
		AlertCheck alertCheck = new AlertCheck(events);

		ProccessGraph proccessGraph = new ProccessGraph();
		
		Graph newGraph = proccessGraph.proccess(panel.getGraph(), alertCheck.getEvents());
		
		for (Component component:newGraph.getComponents()) {
			System.out.println(component);
		}
	}

}
