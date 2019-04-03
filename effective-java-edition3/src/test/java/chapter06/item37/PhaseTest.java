package chapter06.item37;

import org.junit.jupiter.api.Test;

import static chapter06.item37.Phase.form;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhaseTest {

	@Test
	public void from() {
		//assertThat(form(Phase.SOLID, Phase.LIQUID), is(Transition.MELT));
		//assertThat(form(Phase.LIQUID, Phase.SOLID), is(Transition.FREEZE));
	}

	@Test
	public void mapAndList() {
		System.out.println(Transition.phaseTransitionMap);
	}
}
