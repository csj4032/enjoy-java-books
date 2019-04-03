package chapter06.item37;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static chapter06.item37.Phase.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public enum Transition {
	MELT(SOLID, LIQUID),
	FREEZE(LIQUID, SOLID),
	BOIL(LIQUID, GAS),
	CONDENSE(GAS, LIQUID),
	SUBLIME(SOLID, GAS),
	DEPOSIT(GAS, SOLID);

	private final Phase from;
	private final Phase to;

	private Object obj = Stream.of(values()).collect(groupingBy(t -> t.from, () -> new EnumMap<>(Phase.class), toMap(t -> t.to, t -> t, (x, y) -> y, () -> new EnumMap<>(Phase.class))));

	Transition(Phase from, Phase to) {
		this.from = from;
		this.to = to;
	}


	public Phase getFrom() {
		return from;
	}

	public Phase getTo() {
		return to;
	}


}