package com.stackstate.model;

import java.util.Collection;
import java.util.Collections;
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

	public List<Component> findDependencies(Component co) {
		Map<Collection<String>, List<Component>> collect = this.graph.getComponents().stream()
				.collect(Collectors.groupingBy(Component::getComponentNamesDependsOn));
		List<Component> dependecies = collect.get(Collections.singletonList(co.getId()));
		return dependecies;
	}

}
