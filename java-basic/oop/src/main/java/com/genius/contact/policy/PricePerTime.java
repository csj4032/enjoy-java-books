package com.genius.contact.policy;

import com.genius.contact.Call;
import com.genius.contact.Money;
import com.genius.contact.Policy;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Set;

public class PricePerTime extends Policy {

	private final Money price;
	private final Duration second;

	public PricePerTime(@NotNull Money price, @NotNull Duration second) {
		if (price.isLessThanOrEqual(Money.ZERO)) throw new IllegalArgumentException("invalid price");
		if (second.compareTo(Duration.ZERO) <= 0) throw new IllegalArgumentException("invalid second");
		this.price = price;
		this.second = second;
	}

	@Override
	final protected Money calculate(Set<Call> calls, Money result) {
		Money sum = Money.ZERO;
		for (Call call : calls) {
			Money resultPrice = result.plus(price.times(call.getDuration().getSeconds() / second.getSeconds()));
			if (resultPrice.isLessThanOrEqual(Money.ZERO)) throw new RuntimeException("calculate error");
			sum = sum.plus(resultPrice);
		}
		return result.plus(sum);
	}
}
