package com.stackstate.model.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stackstate.model.Component;
import com.stackstate.model.Event;
import com.stackstate.model.Graph;
import com.stackstate.model.JsonUtils;
import com.stackstate.model.Panel;

public class JsonUtilsTest {

	@Test
	public void testExportingToJson() {

		String json = "{\"id\":\"app\",\"own_state\":1,\"derived_state\":0,\"check_states\":{\"RAM usage\":1},\"depends_on\":[]}";


		Component component1 = new Component("app");
		component1.calculateStates("RAM usage", 1);

		String exportJson = JsonUtils.exportJson(component1);

		assertEquals(json, exportJson);

	}

	@Test
	public void testImportingToJson() {

		String json = "{\"id\":\"app\",\"ownState\":1,\"devivedState\":0,\"checkState\":{\"RAM usage\":1},\"dependsOn\":[]}";


		Component component1 = new Component("app");
		component1.calculateStates("RAM usage", 1);

		Component component = JsonUtils.importJson(json, Component.class);

		assertEquals("app", component.getId());

	}

	@Test
	public void testReadingJsonFromAFile() throws IOException {



	

		Component component = JsonUtils.readJson("component.json", Component.class);
		Event event = JsonUtils.readJson("event.json", Event.class);
		Panel panel = JsonUtils.readJson("panel.json", Panel.class);
		
		
		
		
		panel.getGraph().getComponents().forEach(c-> c.dependsOn(panel.findDependencies(c)));
		
		
		
		
		
		System.out.println(panel);
		assertEquals("app", component.getId());
		assertEquals("db", event.getComponentId());
		
		
	}

	
	@Test
	public void testExportingGraphToJson() throws IOException {
		
		//creating components
		
				Component app = new Component("app");
				Component db = new Component("db");
				
				
				app.dependsOn(Collections.singleton(db));
				db.dependsOn(Collections.singleton(app));
				
				app.calculateStates("CPU load", 0);
				db.calculateStates("CPU load", 0);
				
				Collection<Component> components = new ArrayList<>();
				components.add(db);
				components.add(app);
				
				//creating a graph
				Graph graph = new Graph(components);
				Panel panel = new Panel(graph);
				
				
				//put this in the jsontutils method
				Gson gson = new GsonBuilder()
					     .enableComplexMapKeySerialization()
					     .excludeFieldsWithoutExposeAnnotation()
					     .serializeNulls()
					     .setPrettyPrinting()
					     .create();
				
				String json = gson.toJson(panel);
				System.out.println(json);
				
				
//				//creating events
//				Event event1 = new Event("db", 1, "CPU load", 2);
//				Event event2 = new Event("app", 2, "CPU load", 1);
//			
//				Collection<Event> events = new HashSet<>();
//				Collections.addAll(events, event1, event2);
//				
//
//				ProccessGraph proccessGraph = new ProccessGraph();
//				
//				Graph newGraph = proccessGraph.proccess(graph, events);
//				
//				
				
				
		
	}

}
