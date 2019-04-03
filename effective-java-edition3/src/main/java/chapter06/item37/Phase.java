package chapter06.item37;

import java.util.ArrayList;
import java.util.List;

public enum Phase {

	SOLID, LIQUID, GAS;

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

	public static Transition form(Phase from, Phase to) {
		Transition transition = null;
		for (Transition trans : transitions) {
			if (trans.getFrom().equals(from) && trans.getTo().equals(to)) {
				transition = trans;
				break;
			}
		}
		return transition;
	}
}