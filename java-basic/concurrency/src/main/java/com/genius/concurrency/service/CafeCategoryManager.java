package com.genius.concurrency.service;

import com.genius.concurrency.service.dao.CategoryHandler;
import com.genius.concurrency.service.entity.Category;
import com.genius.concurrency.service.model.CafeCategoryNode;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public enum CafeCategoryManager {

	INSTANCE;

	private final Map<String, CafeCategoryNode> categoryMap = new HashMap<>();

	private void load() {
		log.info("CafeCategory Load");
		for (Category category : CategoryHandler.getInstance().selectAll("category.json")) {
			categoryMap.put(category.cateid, new CafeCategoryNode(category));
		}
	}

	public Category get(String categoryId) {
		return getNode(categoryId).getCategory();
	}

	public String getFullName(String categoryId) {
		log.info(Thread.currentThread().getName());
		StringBuilder buf = new StringBuilder();
		CafeCategoryNode node = getNode(categoryId);
		CafeCategoryNode parent = node.getParent();
		if (parent != null) buf.append(parent.getName()).append(" > ");
		buf.append(node.getName());
		return buf.toString();
	}

	private synchronized CafeCategoryNode getNode(String categoryId) {
		if (categoryMap.isEmpty()) load();
		return categoryMap.get(categoryId);
	}
}