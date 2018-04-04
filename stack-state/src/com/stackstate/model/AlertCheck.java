package com.stackstate.model;

import java.util.Collection;

import com.google.gson.annotations.Expose;

public class AlertCheck {

	
	@Expose
	private Collection<Event> events;

	public AlertCheck(Collection<Event> events) {
		
		this.events = events;
	
	}
	
	
	public Collection<Event> getEvents() {
		return events;
	}

}
