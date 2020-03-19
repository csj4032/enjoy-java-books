package com.genius.dispatch;

public class Plus implements Expression {

	private Expression first;
	private Expression second;

	public Plus(Expression first, Expression second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public void visit(Visitor visitor) {
		visitor.visitPlus(this);
	}

	public Expression getFirst() {
		return first;
	}

	public Expression getSecond() {
		return second;
	}
}