package com.stackstate.model.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import com.stackstate.model.Component;
import com.stackstate.model.Event;
import com.stackstate.model.Graph;
import com.stackstate.model.ProccessGraph;

public class ProccessGraphTest {

	@Test
	public void testProccessGraphWithOneEvent() {

		//creating components
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		
		app.dependsOn(Collections.singleton(db));
		db.dependsOn(Collections.singleton(app));
		
		app.calculateStates("CPU load", 0);
		db.calculateStates("CPU load", 0);
		
		Collection<Component> components = new HashSet<>();
		Collections.addAll(components, app, db);
		
		//creating a graph
		Graph graph = new Graph(components);
		
		
		//creating events
		Event event1 = new Event("db", 1, "CPU load", 2);
		Event event2 = new Event("app", 2, "CPU load", 1);
	
		Collection<Event> events = new HashSet<>();
		Collections.addAll(events, event1, event2);
		

		ProccessGraph proccessGraph = new ProccessGraph();
		
		Graph newGraph = proccessGraph.proccess(graph, events);
		
		for (Component component:newGraph.getComponents()) {
			System.out.println(component);
		}
	}

}
