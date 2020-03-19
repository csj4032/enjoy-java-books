package com.genius.dispatch;

public interface Visitor {
	void visitPlus(Plus s);
	void visitMinus(Minus s);
	void visitNumber(Number n);
}