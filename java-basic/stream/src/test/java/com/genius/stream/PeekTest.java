package com.genius.stream;

import com.genius.stream.PersonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

@Slf4j
public class PeekTest {

	@Test
	public void peek() {
		Stream.of(new Mutable(), new Mutable()).peek(m -> m.setV1(m.getV1().replace("-", ""))).forEach(m -> log.info("{}", m));
		Stream.of(new Mutable(), new Mutable()).peek(m -> m.setV1(m.getV1().replaceAll("-", ""))).forEach(m -> log.info("{}", m));
		Person person1 = new PersonBuilder().name("A").address("AA").build();
		Person person2 = new PersonBuilder().name("B").address("BB").build();
		Stream.of(person1, person2).peek(p -> p = new PersonBuilder().name("C").address("CC").build()).forEach(m -> log.info("{}", m));
	}
}
