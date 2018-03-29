package com.stackstate.model.test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.stackstate.model.Component;

public class ComponentTest {

	@Test
	public void testCalculationOfOwnState() {

		
		
		
		Component component = new Component("app");
		component.calculateStates("CPU load", 1);
		component.calculateStates("RAM Usage", 0);
		component.calculateStates("Anther Usage", 2);
		
		
		assertEquals(2, component.getOwnState());
		
	}
	
	@Test
	public void testCalculationOfOwnStateWithZeroCheckStates() {
		
		
		
		
		Component component = new Component("app");
		
		assertEquals(0, component.getOwnState());
		
	}
	
	
	@Test
	public void testCalculationOfDerivedStateBasedInOwnState() {
		
		
		
		
		Component component = new Component("app");
		component.calculateStates("Another Usage", 2);
		
		assertEquals(2, component.getDevivedState());
		
		
		
	}
	
	@Test
	public void testCalculationOfDerivedStateBasedInDerivedStatesOfDependentComponent() {
		
		
		
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		app.dependsOn(Collections.singleton(db));
		db.dependsOn(Collections.singleton(app));
		
		db.calculateStates("Another Usage", 0);
		db.calculateStates("Another Usage", 2);
		
		assertEquals(2, app.getDevivedState());
		
		
		
	}
	

	@Test
	public void testCalculationOfDerivedStateWithDependentsDerivedStateSettedNoData() {
		
		
		
		
		Component app = new Component("app");
		
		
		
		Component db = new Component("db");
		app.dependsOn(Collections.singleton(db));
		
		
		app.calculateStates("Another Usage", 0);
		db.calculateStates("Another Usage", 1);
		assertEquals(0, app.getDevivedState());
		
		
		
	}
	
	
	
	@Test
	public void testCalculationOfDerivedStateWithComponentsDependentsOnEachOther() {
		
		
				
				Component app = new Component("app");
				Component db = new Component("db");
				
				
				app.dependsOn(Collections.singleton(db));
				db.dependsOn(Collections.singleton(app));
				
				app.calculateStates("CPU load", 0);
				db.calculateStates("CPU load", 2);

				assertEquals(2, app.getDevivedState());
				assertEquals(2, db.getDevivedState());
		
		
	}
	
	
	@Test
	public void testCalculationOfDerivedStateWithCyclicDependency() {
		
		
		
		
		Component component1 = new Component("app");
		Component component2 = new Component("db" );
		Component component3 = new Component("server");
		
		
		component1.dependsOn(Collections.singleton(component2));
		component2.dependsOn(Collections.singleton(component3));
		component3.dependsOn(Collections.singleton(component1));
		
		component1.calculateStates("RAM usage",1);
		component2.calculateStates("RAM usage", 0);
		component3.calculateStates("RAM usage", 2);
		
		
		assertEquals(2, component1.getDevivedState());
		assertEquals(2, component2.getDevivedState());
		assertEquals(2, component3.getDevivedState());
		
		
		
	}
	
	@Test
	public void testCalculationOfDerivedStateDependsOnItself(){
		
		
		
		
		Component component1 = new Component("app");
		component1.dependsOn(Collections.singleton(component1));
		
		

		
		component1.calculateStates("RAM usage",2);
		assertEquals(2, component1.getDevivedState());
		
		
		
	}
}
