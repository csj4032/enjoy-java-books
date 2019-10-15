package com.genius.contact;

import java.util.Set;

public abstract class Policy {

	Money calc(Set<Call> calls, Money result) {
		return calculate(calls, result);
	}

	abstract protected Money calculate(Set<Call> calls, Money result);
}