package com.genius.dispatch;

import org.junit.jupiter.api.Test;

public class PlusTest {

	@Test
	public void doSomething() {
		var plus = new Plus();
		plus.visit(new Visitor() {
			@Override
			public void execute(Expression expression) {

			}
		});
	}
}