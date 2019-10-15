package com.genius.contact;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Plan {

	private Calculator calc = new Calculator();
	private Set<Call> calls = new HashSet<>();

	public final void addCall(@NotNull Call call) {
		calls.add(call);
	}

	public final void setCalculator(@NotNull Calculator calc) {
		this.calc = calc;
	}

	public final Money calculateFee() {
		return calls.size() == 0 ? Money.ZERO : calc.calcCallFee(calls, Money.ZERO);
	}
}