package com.stackstate.model.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.stackstate.model.Component;

public class ComponentTest {

	@Test
	public void testCalculationOfOwnState() {

		
		Map<String, Integer> checkState = new HashMap<>();
		
		checkState.put("CPU load", 1);
		checkState.put("RAM Usage", 0);
		checkState.put("Anther Usage", 2);
		
		Component component = new Component("app", checkState);
	
		assertEquals(2, component.getOwnState());
		
	}
	
	@Test
	public void testCalculationOfOwnStateWithZeroCheckStates() {
		
		
		Map<String, Integer> checkState = new HashMap<>();
		
		
		Component component = new Component("app", checkState);
		
		assertEquals(0, component.getOwnState());
		
	}
	
	
	@Test
	public void testCalculationOfDerivedStateBasedInOwnState() {
		
		
		Map<String, Integer> checkState = new HashMap<>();
		
		checkState.put("Another Usage", 2);
		
		Component component = new Component("app", checkState);

		
		assertEquals(2, component.getDevivedState());
		
		
		
	}
	
	@Test
	public void testCalculationOfDerivedStateBasedInDerivedStatesOfDependentComponent() {
		
		
		
		Map<String, Integer> appCheckState = new HashMap<>();
		
		appCheckState.put("Another Usage", 0);
		Component app = new Component("app", appCheckState);

		
		Map<String, Integer> dbCheckState = new HashMap<>();
		
		dbCheckState.put("Another Usage", 2);
		Component db = new Component("db", dbCheckState);
		
		Set<Component> hashSet = new HashSet<>();
		hashSet.add(db);
		
		app.setDependsOn(hashSet);
		
		
		assertEquals(2, app.getDevivedState());
		
		
		
	}

	@Test
	public void testCalculationOfDerivedStateWithDependentsDerivedStateSettedNoData() {
		
		
		
		Map<String, Integer> appCheckState = new HashMap<>();
		
		appCheckState.put("Another Usage", 0);
		Component app = new Component("app", appCheckState);
		
		
		Map<String, Integer> dbCheckState = new HashMap<>();
		
		dbCheckState.put("Another Usage", 1);
		Component db = new Component("db", dbCheckState);
		
		
		app.dependsOn(db);
		assertEquals(0, app.getDevivedState());
		
		
		
	}
	
	@Test
	public void testCalculationOfDerivedStateWithComponentsDependentsOnEachOther() {
		
		
		
		Map<String, Integer> appCheckState = new HashMap<>();
		
		appCheckState.put("Another Usage", 0);
		Component app = new Component("app", appCheckState);
		
		Map<String, Integer> dbCheckState = new HashMap<>();
		
		dbCheckState.put("Another Usage", 2);
		Component db = new Component("db", dbCheckState);
		
		
		app.dependsOn(db);
		db.dependsOn(app);
		assertEquals(2, app.getDevivedState());
		assertEquals(2, db.getDevivedState());
		
		
		
	}

}
