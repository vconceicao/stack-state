package com.stackstate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

	


	@Expose
	@SerializedName("component")
	private String componentId;

	@Expose
	@SerializedName("timestamp")
	private int timeStamp;

	@Expose
	@SerializedName("check_state")
	private String checkStateId;

	@Expose
	private StateEnum state;

	
	
	
	 
	public Event(String componentId, int timeStamp, String checkStateId, StateEnum state) {
		super();
		this.componentId = componentId;
		this.timeStamp = timeStamp;
		this.checkStateId = checkStateId;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Event [componentId=" + componentId + ", timeStamp=" + timeStamp + ", checkStateId=" + checkStateId
				+ ", state=" + state + "]";
	}

	public String getComponentId() {
		return componentId;
	}

	public int getTimeStamp() {
		return timeStamp;
	}

	public String getCheckStateId() {
		return checkStateId;
	}

	public StateEnum getState() {
		return state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkStateId == null) ? 0 : checkStateId.hashCode());
		result = prime * result + ((componentId == null) ? 0 : componentId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + timeStamp;
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
		Event other = (Event) obj;
		if (checkStateId == null) {
			if (other.checkStateId != null)
				return false;
		} else if (!checkStateId.equals(other.checkStateId))
			return false;
		if (componentId == null) {
			if (other.componentId != null)
				return false;
		} else if (!componentId.equals(other.componentId))
			return false;
		if (state != other.state)
			return false;
		if (timeStamp != other.timeStamp)
			return false;
		return true;
	}

	
	
	
}