package com.stackstate.model.test;

import org.junit.Test;

import com.stackstate.model.StackState;

public class StackStateTest {

	@Test(expected=NullPointerException.class)
	public void testIfTheFilesAreNull() {


		String[] args = null;
		StackState.checkFiles(args);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfOneFileisMissing() {
		
		
		String[] args = {"for", };
		StackState.checkFiles(args);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidNumberOfParameters() {
		
		
		String[] args = {"for", "for", "for", "for" };
		StackState.checkFiles(args);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfTheParametersAreJsonFiles() {
		
		
		String[] args = {"for"};
		StackState.checkFiles(args);
	}

}
