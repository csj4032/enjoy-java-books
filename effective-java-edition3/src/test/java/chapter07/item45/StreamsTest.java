package chapter07.item45;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamsTest {

	@Test
	public void matcher() {
		var streams = new Streams();
		Pattern pattern = Pattern.compile("[a-z]");
		streams.matcher(pattern,"1");

		List<String> strings = List.of("a", "b", "c", "d");
		long count = strings.stream().filter(pattern.asPredicate()).count();
		Assertions.assertEquals(4, count);
	}

	@Test
	@Order(6)
	public void localFinalVariable() {
		var a = 10;
		Stream.of(1, 2, 3, 4, 5).filter(e -> {
			int aa = 5;
			return aa < e;
		}).forEach(System.out::println);

		for (int i = 0; i < 10; i++) {
			a = 5;
			if (a > i) {
				System.out.println(i);
			}
		}
	}

	@Test
	public void returnBreakContinue() {
		Stream.of(1, 2, 3, 4, 5).map(e -> {
			if (e > 10) {
				return 0;
			}
			return e;
		});

		for (int i = 0; i < 5; i++) {
			continue;
		}

		for (int i = 0; i < 5; i++) {
			break;
		}

		for (int i = 0; i < 5; i++) {
			return;
		}
	}
}
