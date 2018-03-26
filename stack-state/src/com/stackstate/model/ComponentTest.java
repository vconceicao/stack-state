package com.stackstate.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ComponentTest {

	
	
	public static void main(String[] args) {
		
		Map<String, Integer> checkState = new HashMap<>();
		checkState.put("CPU load", 0);
		checkState.put("RAM Usange", 0);
		
		Component app = new Component("app", 0, 0, checkState );
		
		Component db = new Component("db", 0, 0, checkState );

		app.setDependsOn(Arrays.asList(db));
		db.setDependsOn(Arrays.asList(app));
		
		System.out.println(db);

		db.getDependsOn().forEach(c->System.out.print(c.getId()));
		
	}
}
