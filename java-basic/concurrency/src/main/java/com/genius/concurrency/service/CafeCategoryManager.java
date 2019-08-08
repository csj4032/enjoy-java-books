package com.genius.concurrency.service;

import com.genius.concurrency.service.entity.Category;
import com.genius.concurrency.service.model.CafeCategoryNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public enum CafeCategoryManager {

	INSTANCE;

	private Map<String, CafeCategoryNode> categoryMap = new HashMap<>();

	private void load() {
		log.info("CafeCategory Load");
		Category[] categoryArray = new Gson().fromJson(getJson(), new TypeToken<Category[]>() {}.getType());
		for (Category category : categoryArray) {
			categoryMap.put(category.cateid, new CafeCategoryNode(category));
		}
	}

	public Category get(String categoryId) {
		CafeCategoryNode node = getNode(categoryId);
		return node == null ? null : node.getCategory();
	}

	private synchronized CafeCategoryNode getNode(String categoryId) {
		if (categoryMap.isEmpty()) load();
		return categoryMap.get(categoryId);
	}

	public String getFullName(String categoryId) {
		StringBuilder buf = new StringBuilder();
		CafeCategoryNode node = getNode(categoryId);
		CafeCategoryNode parent = node.getParent();
		if (parent != null) buf.append(parent.getName()).append(" > ");
		buf.append(node.getName());
		return buf.toString();
	}

	private InputStreamReader getJson() {
		try {
			return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("category.json"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}