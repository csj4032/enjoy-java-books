package com.genius.concurrency.service.dao;

import com.genius.concurrency.service.entity.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CategoryHandler {

	private static final CategoryHandler INSTANCE = new CategoryHandler();

	public static CategoryHandler getInstance() {
		return INSTANCE;
	}

	public Category[] selectAll(String name) {
		return new Gson().fromJson(getJson(name), new TypeToken<Category[]>() {}.getType());
	}

	private InputStreamReader getJson(String name) {
		try {
			return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(name), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
