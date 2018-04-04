package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

public class Panel {

	@Expose
	private Graph graph;

	public Panel(Graph graph) {
		this.graph = graph;
	}

	@Override
	public String toString() {
		return "Panel [graph=" + graph + "]";
	}

	public Graph getGraph() {
		// TODO Auto-generated method stub
		return this.graph;
	}

	private Collection<Component> findComponentsDependsOn(Component c) {

		Collection<Component> components = new HashSet<>();

		for (String componentId : c.getComponentNamesDependsOn()) {
			Component component = this.getGraph().getComponentsMap().get(componentId);
			components.add(component);
		}
		return components;

	}

	public void build() {
		
		this.graph.getComponents().stream().filter(c -> c.getComponentNamesDependencyOf() != null)
				.forEach(c -> c.dependencyOf(findComponentDependenciesOf(c)));
		
		this.graph.getComponents().stream().filter(c -> c.getComponentNamesDependsOn() != null)
				.forEach(c -> c.dependsOn(findComponentsDependsOn(c)));
	}

	private Collection<Component> findComponentDependenciesOf(Component c) {
		Collection<Component> components = new HashSet<>();

		if (c.getComponentNamesDependencyOf() != null) {

			for (String componentId : c.getComponentNamesDependencyOf()) {
				Component component = this.getGraph().getComponentsMap().get(componentId);
				components.add(component);
			}

			return components;
		} else {
			return null;
		}
	}

}
