package chapter06.item37;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static chapter06.item37.Transition.formList;
import static chapter06.item37.Transition.formMap;
import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhaseTest {

	@Test
	public void from() {
		assertThat(formList(Phase.SOLID, Phase.LIQUID), is(Transition.MELT));
		assertThat(formList(Phase.LIQUID, Phase.SOLID), is(Transition.FREEZE));
		assertThat(formMap(Phase.LIQUID, Phase.LIQUID), is(Transition.MELT));
		assertThat(formMap(Phase.LIQUID, Phase.SOLID), is(Transition.FREEZE));
	}

	@Test
	public void mapAndList() {
		Transition[] transitions = Transition.values();
		Map<Phase, List<Transition>> m1 = Stream.of(transitions).collect(groupingBy(t -> t.getFrom()));

		Map<Phase, List<Transition>> m2 = Stream.of(transitions).collect(groupingBy(t -> t.getFrom(), toList()));

		Map<Phase, List<Transition>> m3 = Stream.of(transitions).collect(groupingBy(t -> t.getFrom(), HashMap::new, toList()));

		Map<Phase, List<Transition>> m4 = Stream.of(transitions).collect(groupingBy(t -> t.getFrom(), () -> new EnumMap<>(Phase.class), toList()));
		Map<Phase, Map<Phase, Transition>> m5 =
				Stream.of(transitions).collect(groupingBy(t -> t.getFrom(), () -> new EnumMap<>(Phase.class), toMap(t -> t.getTo(), t -> t)));
		Map<Phase, Map<Phase, Map<Transition, Enum<Transition>>>> m6 =
				Stream.of(transitions).collect(groupingBy(t -> t.getFrom(), () -> new EnumMap<>(Phase.class),
						toMap(
								t -> t.getTo(),
								(t)-> new EnumMap<>(Transition.class),
								(x, y) -> x,
								() -> new EnumMap<>(Phase.class))));
	}
}
