package com.stackstate.model.test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.stackstate.model.Component;
import com.stackstate.model.StateEnum;

public class ComponentTest {

	@Test
	public void testCalculationOfOwnState() {

		
		
		
		Component component = new Component("app");
		component.calculateStates("CPU load", StateEnum.CLEAR);
		component.calculateStates("RAM Usage", StateEnum.NO_DATA);
		component.calculateStates("Anther Usage", StateEnum.WARNING);
		
		
		assertEquals(StateEnum.WARNING, component.getOwnState());
		
	}
	
	@Test
	public void testCalculationOfOwnStateWithZeroCheckStates() {
		
		
		
		
		Component component = new Component("app");
		
		assertEquals(StateEnum.NO_DATA, component.getOwnState());
		
	}
	
	
	@Test
	public void testCalculationOfDerivedStateBasedInOwnState() {
		
		
		
		
		Component component = new Component("app");
		component.calculateStates("Another Usage", StateEnum.WARNING);
		
		assertEquals(StateEnum.WARNING, component.getDevivedState());
		
		
		
	}
	
	@Test
	public void testCalculationOfDerivedStateBasedInDerivedStatesOfDependentComponent() {
		
		
		
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		app.dependsOn(Collections.singleton(db));
		db.dependencyOf(Collections.singleton(app));
		
		db.calculateStates("Another Usage", StateEnum.NO_DATA);
		db.calculateStates("Another Usage", StateEnum.WARNING);
		
		assertEquals(StateEnum.WARNING, app.getDevivedState());
		
		
		
	}
	

	public void testCalculationOfDerivedStateWithDependentsDerivedStateSettedNoData() {
		
		
		
		
		Component app = new Component("app");
		Component db = new Component("db");
		
		
		
		app.dependsOn(Collections.singleton(db));
		db.dependencyOf(Collections.singleton(app));
		
		db.calculateStates("Another Usage", StateEnum.NO_DATA);
		
		assertEquals(StateEnum.NO_DATA, app.getDevivedState());
		
		
	}
	
	
	
	@Test
	public void testCalculationOfDerivedStateWithComponentsDependentsOnEachOther() {
		
		
				
				Component app = new Component("app");
				Component db = new Component("db");
				
				
				app.dependsOn(Collections.singleton(db));
				db.dependencyOf(Collections.singleton(app));
				
				db.calculateStates("CPU load", StateEnum.WARNING);

				assertEquals(StateEnum.WARNING, app.getDevivedState());
				assertEquals(StateEnum.WARNING, db.getDevivedState());
		
		
	}
	
	
	@Test
	public void testCalculationOfDerivedStateWithCyclicDependency() {
		
		
		
		//review this test
		Component component1 = new Component("app");
		Component component2 = new Component("db" );
		Component component3 = new Component("server");
		
		
		component1.dependsOn(Collections.singleton(component2));
		component1.dependsOn(Collections.singleton(component3));
		component2.dependencyOf(Collections.singleton(component3));
		component3.dependencyOf(Collections.singleton(component1));
		
		component1.calculateStates("RAM usage",StateEnum.CLEAR);
		component2.calculateStates("RAM usage", StateEnum.NO_DATA);
		component3.calculateStates("RAM usage", StateEnum.WARNING);
		
		
		assertEquals(2, component1.getDevivedState());
		assertEquals(2, component3.getDevivedState());
		
		
		
	}
	
	@Test
	public void testCalculationOfDerivedStateDependsOnItself(){
		
		
		
		
		Component component1 = new Component("app");
		component1.dependsOn(Collections.singleton(component1));
		
		

		
		component1.calculateStates("RAM usage",StateEnum.WARNING);
		assertEquals(StateEnum.WARNING, component1.getDevivedState());
		
		
		
	}
}
