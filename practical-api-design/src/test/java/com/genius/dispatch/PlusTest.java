package com.genius.dispatch;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class PlusTest {

	@Test
	public void doSomething() {
		Number n1 = new Number(1);
		Number n2 = new Number(2);
		var plus = new Plus(n1, n2);
		var print = new PrintVisitor();
		plus.visit(print);
		log.info(print.getStringBuilder().toString());
		Assertions.assertEquals("1 + 2", print.getStringBuilder().toString());
	}
}