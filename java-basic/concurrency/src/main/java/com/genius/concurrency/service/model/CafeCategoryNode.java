package com.genius.concurrency.service.model;

import com.genius.concurrency.service.entity.Category;

import java.util.ArrayList;

public class CafeCategoryNode {
	private CafeCategoryNode parent;
	private final Category cate;
	private ArrayList<CafeCategoryNode> children;

	public CafeCategoryNode(Category cate) {
		this.cate = cate;
	}

	public void setParent(CafeCategoryNode node) {
		this.parent = node;
	}

	public CafeCategoryNode getParent() {
		return this.parent;
	}

	public void addChildren(CafeCategoryNode node) {
		if (this.children == null) {
			this.children = new ArrayList();
		}
		this.children.add(node);
	}

	public ArrayList<CafeCategoryNode> getChildren() {
		return this.children;
	}

	public Category getCategory() {
		return this.cate;
	}

	public String getId() {
		return this.cate.cateid;
	}

	public boolean hasParent() {
		return (this.cate.parcateid != null && !"".equals(this.cate.parcateid) && !" ".equals(this.cate.parcateid));
	}

	public String getName() {
		return this.cate.catename;
	}
}
