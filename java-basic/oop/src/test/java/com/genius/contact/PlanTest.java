package com.genius.contact;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlanTest {

	@Test
	@Order(1)
	@DisplayName("Call 없을 경우 계산 시도")
	public void callByZero() {
		Plan plan = new Plan();
		Assertions.assertEquals(Money.ZERO, plan.calculateFee());
	}

	@Test
	@Order(2)
	@DisplayName("Policy 없을 경우 계산 시도 : IllegalArgumentException")
	public void policyByZero() {
		Plan plan = new Plan();
		//plan.addCall(new Call(LocalDateTime.of(2016, Month.APRIL, 30, 0, 0), LocalDateTime.of(2016, Month.APRIL, 30, 0, 0)));
		plan.addCall(new Call(null, null));
		Assertions.assertThrows(IllegalArgumentException.class, () -> plan.calculateFee());
	}
}