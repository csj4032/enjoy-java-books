package com.genius.contact.policy;

import com.genius.contact.Call;
import com.genius.contact.Money;
import com.genius.contact.Policy;

import java.util.Set;

public class Tex extends Policy {

	private final double ratio;

	public Tex(double ratio) {
		this.ratio = ratio;
	}

	@Override
	final protected Money calculate(Set<Call> calls, Money result) {
		return result.plus(result.times(ratio));
	}
}
