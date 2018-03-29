package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Component {


	@Expose
	private String id;
	
	@Expose
	@SerializedName("own_state")
	private int ownState;
	
	@Expose
	@SerializedName("derived_state")
	private int devivedState;
	
	@Expose
	@SerializedName("check_states")
	private Map<String, Integer> checkState = new HashMap<>();
	
	@Expose(serialize = false)
	private Collection<Component> dependsOn = new  HashSet<>();
	
	@Expose
	@SerializedName("depends_on")
	private Collection<String> componentNamesDependsOn = new HashSet<>();
	
	
	public Collection<String> getComponentNamesDependsOn() {
		return componentNamesDependsOn;
	}


	public Collection<Component> getDependsOn() {
		return dependsOn;
	}


	public Component(String id) {
		this.id = id;
		this.dependsOn = new HashSet<>();
	}


	private void calculateOwnState() {
				
				if (checkState.values().size()==0) {
					this.ownState = 0;
				}else{
				Integer max = Collections.max(checkState.values());
				this.ownState = max;
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



	public int getOwnState() {
		calculateOwnState();
		return ownState;
		
	}




	public int getDevivedState() {
		return devivedState;
	}


	private void calculateDerivedState() {
		if(this.getOwnState()>=2){
			this.devivedState = ownState;
		}else if(getDependentHighestDerivedState()>=2){
			this.devivedState = getDependentHighestDerivedState();
		}else{
			this.devivedState = 0;
		}
	}








	private int getDependentHighestDerivedState() {
		
		if (!dependsOn.isEmpty()) {
			
		List<Integer> devivedStates = this.dependsOn.stream().map(Component::getDevivedState).collect(Collectors.toList());
		
		//get the highest one
		int dependentHighestderivedState = Collections.max(devivedStates);
		
		//check if the highest derived state is major or equal than warning
		return dependentHighestderivedState;
		}else{
			return -1;
		}
	}


	public Map<String, Integer> getCheckState() {
		return checkState;
	}

	private void setDerivedState(int derivedState) {
		
		this.devivedState = derivedState;
		this.dependsOn.forEach(c->c.calculateDerivedState());
	}

	public void dependsOn(Collection<Component> components) {
		
		this.dependsOn = components;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Component other = (Component) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public void calculateStates(String checkStateId, int state) {

		this.getCheckState().put(checkStateId, state);
		calculateDerivedState();
		
		//propagating derived state
		if (this.devivedState>=2 && !dependsOn.isEmpty()) {
			this.dependsOn.forEach(c -> c.setDerivedState(this.devivedState));
		}

	}



	
}
