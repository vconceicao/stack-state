package com.stackstate.model;

import java.util.Collection;
import java.util.Map;

public class Component {

	private String id;
	private int ownState;
	private int devivedState;
	private Map<String, Integer> checkState;
	private Collection<Component> dependsOn;
	
	
	public Collection<Component> getDependsOn() {
		return dependsOn;
	}





	public void setDependsOn(Collection<Component> dependsOn) {
		this.dependsOn = dependsOn;
	}





	public Component(String id, int ownState, int devivedState, Map<String, Integer> checkState) {
		this.id = id;
		this.ownState = ownState;
		this.devivedState = devivedState;
		this.checkState = checkState;
		;
		
	}


	


	@Override
	public String toString() {
		return "Component [id=" + id + ", ownState=" + ownState + ", devivedState=" + devivedState + ", checkState="
				+ checkState + "]";
	}





	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getOwnState() {
		return ownState;
	}


	public void setOwnState(int ownState) {
		this.ownState = ownState;
	}


	public int getDevivedState() {
		return devivedState;
	}


	public void setDevivedState(int devivedState) {
		this.devivedState = devivedState;
	}


	public Map<String, Integer> getCheckState() {
		return checkState;
	}


	public void setCheckState(Map<String, Integer> checkState) {
		this.checkState = checkState;
	}

	
	
}
