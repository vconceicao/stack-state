package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProccessGraph {

	public Graph proccess(Graph graph, Collection<Event> events) {

		
		//ordering events by timeStamp
		List<Event> eventList = events.stream().collect(Collectors.toList());
		Collections.sort(eventList, Comparator.comparing(Event::getTimeStamp));
		
		//tranforming a graph components into a map
		Map<String, Component> map = graph.getComponents().stream().collect(Collectors.toMap(Component:: getId, c->c));

		//iterating eventList
		Collection<Component> components = new HashSet<>();
		
		for (Event event : eventList) {
			 
			//getting a component from a map
			if (map.get(event.getComponentId())!=null) {
				Component component = map.get(event.getComponentId()); 
						
				//setting the new check state
				component.calculateStates(event.getCheckStateId(), event.getState());
				
				
				
				//adding the component to a collection
				components.add(component);
				
			}
		}
		
		
		Graph graph2 = new Graph(components);
		return graph2;
		
	}

	
	
	
}
