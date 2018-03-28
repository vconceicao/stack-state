package com.stackstate.model.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;

import com.stackstate.model.Component;
import com.stackstate.model.Event;
import com.stackstate.model.Graph;
import com.stackstate.model.ProccessGraph;

public class ProccessGraphTest {

	@Test
	public void testProccessGraphWithOneEvent() {

		//creating components
		Map<String, Integer> appCheckState = new HashMap<>();
		
		appCheckState.put("CPU load", 0);
		Component app = new Component("app", appCheckState);
		
		Map<String, Integer> dbCheckState = new HashMap<>();
		
		dbCheckState.put("CPU load", 0);
		Component db = new Component("db", dbCheckState);
		
		
		app.dependsOn(db);
		db.dependsOn(app);
		
		
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
