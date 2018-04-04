package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Component {

	@Expose
	private String id;

	@Expose
	@SerializedName("own_state")
	private StateEnum ownState;

	@Expose
	@SerializedName("derived_state")
	private StateEnum derivedState;

	@Expose
	@SerializedName("check_states")
	private Map<String, StateEnum> checkState = new HashMap<>();

	@Expose(serialize = false, deserialize = false)
	private Collection<Component> dependsOn = new HashSet<>();

	@Expose(serialize = false, deserialize = false)
	private Collection<Component> dependencyOf = new HashSet<>();

	@Expose
	@SerializedName("depends_on")
	private Collection<String> componentNamesDependsOn = new HashSet<>();

	@Expose
	@SerializedName("dependency_of")
	private Collection<String> componentNamesDependencyOf = new HashSet<>();

	public Collection<String> getComponentNamesDependsOn() {
		return componentNamesDependsOn;
	}

	public Collection<String> getComponentNamesDependencyOf() {
		return componentNamesDependencyOf;
	}

	public Collection<Component> getDependsOn() {
		return dependsOn;
	}

	public Component(String id) {
		this.id = id;
	}

	private void calculateOwnState() {

		if (checkState.values().size() == 0) {
			this.ownState = StateEnum.NO_DATA;
		} else {
			StateEnum max = Collections.max(checkState.values());
			this.ownState = max;
		}
	}

	@Override
	public String toString() {
		return "Component [id=" + id + ", ownState=" + ownState + ", devivedState=" + derivedState + ", checkState="
				+ checkState + "]";
	}

	public String getId() {
		return id;
	}

	public StateEnum getOwnState() {
		calculateOwnState();
		return ownState;

	}

	public StateEnum getDevivedState() {
		return derivedState;
	}

	private void calculateDerivedState() {
		if (this.getOwnState().ordinal() >= 2) {
			this.derivedState = ownState;
		} else if (getDependentHighestDerivedState().ordinal() >= 2) {
			this.derivedState = getDependentHighestDerivedState();
		} else {
			this.derivedState = StateEnum.NO_DATA;
		}
	}

	private StateEnum getDependentHighestDerivedState() {

		if (dependsOn!=null && !dependsOn.isEmpty()) {

			List<StateEnum> derivedStates = this.dependsOn.stream().filter(c-> c.getDevivedState()!=null).map(Component::getDevivedState)
					.collect(Collectors.toList());

			// get the highest one
			if (derivedStates.isEmpty()) {
				throw new NoSuchElementException("Dependent component don't have check states");
			}
			
			StateEnum dependentHighestderivedState = Collections.max(derivedStates);

			// check if the highest derived state is major or equal than warning
			return dependentHighestderivedState;
		} else {
			return StateEnum.NO_DATA;
		}
	}

	public Map<String, StateEnum> getCheckState() {
		return checkState;
	}

	

	public void dependsOn(Collection<Component> components) {

		this.dependsOn = components;
	}

	public void dependencyOf(Collection<Component> components) {

		this.dependencyOf = components;

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

	public void calculateStates(String checkStateId, StateEnum state) {

		this.getCheckState().put(checkStateId, state);
		calculateDerivedState();

		// propagating derived state
		if (this.derivedState.ordinal()>=2 & dependencyOf != null) {
			this.dependencyOf.forEach(c -> c.calculateDerivedState());

		}
	}

}
