package chapter07.item47;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamCollection {

	@Test
	public void streamVsIterable() {
		Iterator<Integer> iterator = Stream.of(1, 2, 3, 4, 5).iterator();
		Assertions.assertEquals(1, iterator.next());
		Iterable iterable = iterableOf(Stream.of(1, 2, 3, 4));
		consumer((t) -> System.out.println(t));
	}

	public static <E> Iterable<E> iterableOf(Stream<E> stream) {
		//Iterator<T> iterator();
		//return () -> stream.iterator();
		//한정적 메스드 레퍼런스
		return stream::iterator;
	}

	public static <T> Consumer<T> consumer(Consumer<T> consumer) {
		return consumer::accept;
	}
}
