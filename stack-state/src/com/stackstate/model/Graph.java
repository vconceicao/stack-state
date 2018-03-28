package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;

public class Graph {

	
	private Collection<Component> components;

	public Graph(Collection<Component> components) {
		this.components = components;
	}
	
	public Collection<Component> getComponents() {
		return Collections.unmodifiableCollection(components) ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components == null) ? 0 : components.hashCode());
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
		Graph other = (Graph) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		return true;
	}
}
