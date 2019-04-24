package chapter07.item46;

import com.google.common.base.Functions;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.stream.Collectors.*;
import static org.jooq.lambda.Agg.count;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SideEffectTest {

	private static List<Album> albums;
	private static List<String> list;
	private static Map<String, Long> map;

	@BeforeAll
	public static void setUp() {
		albums = List.of(new Album(new Artist("A"), 100), new Album(new Artist("B"), 200));
		list = List.of("AA", "AA", "B", "B", "C", "C", "D", "AA", "AA", "B", "B", "E", "E", "E", "F", "G", "H", "I", "H");
		map = list.stream().collect(Collectors.groupingBy(Functions.identity(), count()));
	}

	@Test
	@Order(1)
	public void lazyEvaluation() {
		Stream.iterate(0, a -> a + 1);
		Stream.iterate(0, a -> a + 1).limit(10);
		Stream.iterate(0, a -> a + 1).limit(10).forEach(System.out::println);
	}

	@Test
	@Order(2)
	public void fluent() {
		Stream.of(1, 2, 3).filter(e -> e > 2).map(e -> e * 10).forEach(System.out::println);
	}

	@Test
	@Order(3)
	public void effectTest() {
		var effect = new SideEffect();
		effect.effect();
	}

	@Test
	@Order(4)
	public void topTen() {
		List<String> sort = map.keySet().stream().sorted((m1, m2) -> map.get(m2).compareTo(map.get(m1))).limit(10).collect(toList());
		List<String> sort2 = map.keySet().stream().sorted(comparing(map::get).reversed()).limit(10).collect(toList());
		Assertions.assertEquals(sort, sort2);
	}

	@Test
	@Order(5)
	public void mergeFunction() {
		Map<Artist, Album> topHits = albums.stream().collect(toMap(Album::getArtist, a -> a, maxBy(comparing(Album::getSales))));
	}

	@Test
	public void groupingBy() {
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.groupingBy((a) -> a));
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.groupingBy((a) -> a, toList()));
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.groupingBy(Functions.identity(), HashMap::new, toList()));
		Stream.of(1, 1, 1, 2, 5).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, toCollection(() -> new ArrayList())));
		Stream.of(1, 1, 1, 2, 2).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
		Stream.of(1, 1, 1, 2, 2).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(Integer::intValue)));
		Map<Integer, IntSummaryStatistics> intSummaryStatisticsMap = Stream.of(1, 1, 1, 2, 2).collect(Collectors.groupingBy(Function.identity(), Collectors.summarizingInt(Integer::intValue)));
		Stream.of(1, 1, 1, 2, 2).collect(Collectors.groupingBy(Function.identity(), Collectors.averagingInt(Integer::intValue)));
		Stream.of(1, 5, 6, 2, 2).collect(Collectors.groupingBy(Function.identity(), HashMap<Integer, List<Integer>>::new, Collectors.filtering(e -> e >= 2, toList())));
		Stream.of(1, 5, 6, 2, 2).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.filtering(e -> e >= 2, count())));
	}

	@Test
	public void collectors() {
		Stream.of(1, 2, 3, 4, 5).max(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		Stream.of(1, 2, 3, 4, 5).max((e1, e2) -> e1.compareTo(e2));
		Stream.of(1, 2, 3, 4, 5).max(Integer::compareTo);
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.maxBy((e1, e2) -> e1.compareTo(e2)));
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.maxBy(Comparator.comparingInt(e -> e)));
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.maxBy(Comparator.comparing(e -> e)));
		Stream.of(1, 2, 3, 4, 5).collect(Collectors.maxBy(Integer::compareTo));

		Stream.of("A", "B", "C", "D").collect(Collectors.joining());
		Stream.of("A", "B", "C", "D").collect(Collectors.joining(","));
		Stream.of("A", "B", "C", "D").collect(Collectors.joining(",", "[", "]"));
	}
}