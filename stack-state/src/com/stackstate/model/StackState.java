package com.stackstate.model;

import java.io.IOException;

public class StackState {

	private static final int NUMBER_PARAMETERS = 2;

	public static void main(String[] args) throws IOException {

		checkFiles(args);

		Panel panel = JsonUtils.readJson("panel.json", Panel.class);

		AlertCheck alertCheck = JsonUtils.readJson("alertcheck.json", AlertCheck.class);

		panel.build();

		ProccessGraph proccessGraph = new ProccessGraph();

		Graph graph = proccessGraph.proccess(panel.getGraph(), alertCheck.getEvents());

		Panel panel2 = new Panel(graph);

		String panelExported = JsonUtils.exportJson(panel2);

		System.out.println(panelExported);

	}

	public static void checkFiles(String[] args) {

		if (args == null) {
			throw new NullPointerException("Json files are missing.");
		}

		if (args.length != NUMBER_PARAMETERS) {
			throw new IllegalArgumentException("Invalid number of parameters. Insert two json files");
		}
		
		if (!args[0].contains(".json")) {
			throw new IllegalArgumentException("It must be a json file");

		}

	}

}
