package com.genius.dispatch;

import java.util.Date;

public class PrintVisitor implements Visitor {

	private StringBuilder stringBuilder = new StringBuilder();

	@Override
	public void visitPlus(Plus s) {
		s.getFirst().visit(this);
		stringBuilder.append(" + ");
		s.getSecond().visit(this);
	}

	@Override
	public void visitMinus(Minus s) {

	}

	@Override
	public void visitNumber(Number n) {
		stringBuilder.append(n.getValue());
	}

	public StringBuilder getStringBuilder() {
		return stringBuilder;
	}

	public static void main(String[] args) {
		Date date = new Date("2020-02-06T06:33:32.846");
		System.out.println(date.getTime());
	}
}
