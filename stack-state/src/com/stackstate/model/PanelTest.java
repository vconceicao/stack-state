package com.stackstate.model;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

public class PanelTest {

	
	@Ignore
	@Test
	public void testFindDepenciesOn() {
		
		
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		
		app.dependsOn(Collections.singleton(db));
		db.dependencyOf(Collections.singleton(app));
		
		app.calculateStates("CPU load", StateEnum.NO_DATA);
		db.calculateStates("CPU load", StateEnum.NO_DATA);
		
		Collection<Component> components = new HashSet<>();
		components.add(app);
		components.add(db);
		
		//creating a graph
		Graph graph = new Graph(components);
		
		
		Panel panel = new Panel(graph);
		panel.build();
		
		
		
		
	}
	
	
	@Ignore
	@Test
	public void testFindDepenciesOf() {
		
		
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		
		app.dependsOn(Collections.singleton(db));
		db.dependencyOf(Collections.singleton(app));
		
		app.calculateStates("CPU load", StateEnum.NO_DATA);
		db.calculateStates("CPU load", StateEnum.NO_DATA);
		
		Collection<Component> components = new HashSet<>();
		components.add(app);
		components.add(db);
		
		//creating a graph
		Graph graph = new Graph(components);
		
		
		Panel panel = new Panel(graph);

		panel.build();
		

		
		
		
		
		
	}

}
