package com.genius.dispatch;

public class Number implements Expression {
	private final int value;

	public Number(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public void visit(Visitor v) {
		v.visitNumber(this);
	}
}
