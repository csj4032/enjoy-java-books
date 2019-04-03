package chapter06.item37;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static chapter06.item37.Phase.*;
import static java.util.stream.Collectors.*;

public enum Transition {
	MELT(SOLID, LIQUID),
	FREEZE(LIQUID, SOLID),
	BOIL(LIQUID, GAS),
	CONDENSE(GAS, LIQUID),
	SUBLIME(SOLID, GAS),
	DEPOSIT(GAS, SOLID);

	private final Phase from;
	private final Phase to;

	Transition(Phase from, Phase to) {
		this.from = from;
		this.to = to;
	}

	private static List<Transition> transitions = new ArrayList<>();

	static {
		Transition melt = Transition.MELT;
		Transition freeze = Transition.FREEZE;
		Transition boil = Transition.BOIL;
		Transition condense = Transition.CONDENSE;
		Transition sublime = Transition.SUBLIME;
		Transition deposit = Transition.DEPOSIT;
		transitions.add(melt);
		transitions.add(freeze);
		transitions.add(boil);
		transitions.add(condense);
	}

	public static Transition formList(Phase from, Phase to) {
		Transition transition = null;
		for (Transition trans : transitions) {
			if (trans.getFrom().equals(from) && trans.getTo().equals(to)) {
				transition = trans;
				break;
			}
		}
		return transition;
	}

	public static Transition formMap(Phase from, Phase to) {
		return map.get(from).get(to);
	}

	private static Map<Phase, Map<Phase, Transition>> map =
			Stream.of(values()).collect(groupingBy(t -> t.getFrom(), () -> new EnumMap<>(Phase.class),
					toMap(
							t -> t.getTo(),
							t -> t,
							(x, y) -> x,
							() -> new EnumMap<>(Phase.class))));

	public Phase getFrom() {
		return from;
	}

	public Phase getTo() {
		return to;
	}
}