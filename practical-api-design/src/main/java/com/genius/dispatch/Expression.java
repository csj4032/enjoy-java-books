package com.genius.dispatch;

public interface Expression {

	void visit(Visitor visitor);
}