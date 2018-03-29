package com.stackstate.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private static Gson gson = new Gson();

	public static String exportJson(Object object) {

		String json = gson.toJson(object);

		return json;

	}

	public static <T> T importJson(String json, Class<T> classOfT) {

		return gson.fromJson(json, classOfT);

	}

	public static <T> T  readJson(String jsonfile, Class<T> classOfT ) throws IOException {
		Gson gsonRead = new GsonBuilder()
			     .enableComplexMapKeySerialization()
			     .excludeFieldsWithoutExposeAnnotation()
			     .serializeNulls()
			     .setPrettyPrinting()
			     .create();
		try (Reader reader = new FileReader(jsonfile)) {
			return  gsonRead.fromJson(reader, classOfT);
			
		}

	}
}