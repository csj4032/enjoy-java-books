package com.genius.dispatch;

public class Plus implements Expression {

	@Override
	public void visit(Visitor visitor) {
		visitor.execute(this);
	}
}