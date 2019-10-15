package com.genius.contact;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Calculator {

	private Set<Policy> policies = new HashSet<>();

	public final Calculator setNext(Policy policy) {
		if(Objects.isNull(policy)) throw new IllegalArgumentException("policy is null");
		policies.add(policy);
		return this;
	}

	final Money calcCallFee(Set<Call> calls, Money result) {
		if (policies.isEmpty()) throw new IllegalArgumentException("calc is empty");
		for (Policy policy : policies) result = policy.calc(calls, result);
		if (result.isLessThanOrEqual(Money.ZERO)) throw new RuntimeException("calculate error");
		return result;
	}
}