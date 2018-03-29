package com.stackstate.model;

import com.google.gson.annotations.SerializedName;

public class Event {

	@SerializedName("component")
	private String componentId;

	@SerializedName("timestamp")
	private int timeStamp;

	@SerializedName("check_state")
	private String checkStateId;

	private int state;

	public Event(String componentId, int timeStamp, String checkStateId, int state) {
		this.componentId = componentId;
		this.timeStamp = timeStamp;
		this.checkStateId = checkStateId;
		this.state = state;
	}

	public int getTimeStamp() {
		return timeStamp;
	}

	public String getComponentId() {
		return componentId;
	}

	public String getCheckStateId() {
		return checkStateId;
	}

	public int getState() {
		return state;
	}
}
