package com.stackstate.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stackstate.model.AlertCheck;
import com.stackstate.model.Component;
import com.stackstate.model.Event;
import com.stackstate.model.Graph;
import com.stackstate.model.JsonUtils;
import com.stackstate.model.Panel;
import com.stackstate.model.StateEnum;

public class JsonUtilsTest {

	@Test
	@Ignore
	public void testExportingToJson() {

		String json = "{\"id\":\"app\",\"own_state\":1,\"derived_state\":0,\"check_states\":{\"RAM usage\":1},\"depends_on\":[]}";


		Component component1 = new Component("app");
		component1.calculateStates("RAM usage", StateEnum.CLEAR);

		String exportJson = JsonUtils.exportJson(component1);

		assertEquals(json, exportJson);

	}

	@Test
	public void testImportingToJson() {

		String json = "{\"id\":\"app\",\"ownState\":1,\"devivedState\":0,\"checkState\":{\"RAM usage\":1},\"dependsOn\":[]}";


		Component component1 = new Component("app");
		component1.calculateStates("RAM usage", StateEnum.CLEAR);

		Component component = JsonUtils.importJson(json, Component.class);

		assertEquals("app", component.getId());

	}
	
	

	@Test
	public void testReadingJsonFromAFile() throws IOException {



	

		Component component = JsonUtils.readJson("component.json", Component.class);
		Event event = JsonUtils.readJson("event.json", Event.class);
		Panel panel = JsonUtils.readJson("panel.json", Panel.class);
		AlertCheck alert = JsonUtils.readJson("alertcheck.json", AlertCheck.class);
		
		
		
		
		panel.build();
		
		
		Collection<Event> events = alert.getEvents();
		
		
		
		System.out.println(panel);
		assertEquals("app", component.getId());
		assertEquals("app", event.getComponentId());
		assertTrue(events.contains(event));
		
		
	}

	
	@Test
	public void testExportingGraphToJson() throws IOException {
		
		//creating components
		
				Component app = new Component("app");
				Component db = new Component("db");
				Component server = new Component("server");
				
				
				app.dependsOn(Collections.singleton(db));
				db.dependencyOf(Collections.singleton(app));
				
				db.calculateStates("CPU load", StateEnum.NO_DATA);
				
				Collection<Component> components = new ArrayList<>();
				components.add(db);
				components.add(app);
				components.add(server);
				
				//creating a graph
				Graph graph = new Graph(components);
				Panel panel = new Panel(graph);
				
				
				
				
				String json = JsonUtils.exportJson(panel);
				System.out.println(json);
				
			
		
	}
	
	@Test
	public void testExportStatesToJson() throws IOException {
		
		//creating components
		

		
		String exportJson = JsonUtils.exportJson(StateEnum.NO_DATA);
		
		assertEquals("\"no_data\"", exportJson);

		
	}
	
	@Test
	public void testImportStatesToJson() throws IOException {
		
		//creating components
		
		
		
		StateEnum importJson = JsonUtils.importJson("\"no_data\"", StateEnum.class);
		
		System.out.println(importJson);
		
	}

}
