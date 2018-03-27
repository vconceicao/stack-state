package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Component {

	private String id;
	private int ownState;
	private int devivedState;
	private Map<String, Integer> checkState;
	private Collection<Component> dependsOn = new  HashSet<>();
	
	
	public Collection<Component> getDependsOn() {
		return dependsOn;
	}


	public void setDependsOn(Collection<Component> dependsOn) {
		this.dependsOn = dependsOn;
	}





	public Component(String id,  Map<String, Integer> checkState) {
		this.id = id;
		this.checkState = checkState;


		
	}


	private void calculateOwnState() {
				List<Integer> states = this.checkState.values().stream().collect(Collectors.toList());
				
				if (checkState.values().size()==0) {
					this.ownState = 0;
				}else{
				Collections.sort(states);
				Collections.reverse(states);
				this.ownState = states.get(0);
				}
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
		calculateOwnState();
		
		return ownState;
		
	}


	public void setOwnState(int ownState) {
		this.ownState = ownState;
	}


	public int getDevivedState() {
		
		
		calculateDerivedState();
		return devivedState;
	}


	private void calculateDerivedState() {
		if(this.getOwnState()>=2){
			this.devivedState = ownState;
		}else{
			if(this.dependsOn!=null){
			List<Component> components = this.dependsOn.stream().collect(Collectors.toList());
			components.sort(Comparator.comparing(Component::getDevivedState).reversed());
			this.devivedState = components.get(0).getDevivedState();
			}
		}
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


	public void dependsOn(Component db) {

		this.dependsOn.add(db);
	}


	
}
