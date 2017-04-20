package ch05;

import ch04.Dish;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ch04.LowCaloricDishes.menu;

public class NumberStream {

	public static void main(String[] args) {
		//int calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		int calories = menu.stream().mapToInt(Dish::getCalories).sum();

		//IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		//Stream<Integer> stream = intStream.boxed();

		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
		int max = maxCalories.orElse(1);

		IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);

		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));


		Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 1000000).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 1000000).parallel().mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}).filter(t -> t[2] % 1 == 0));

		List<double[]> doubles = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		//pythagoreanTriples.limit(5).forEach(t -> System.out.println(Arrays.toString(t)));
		//pythagoreanTriples2.limit(50000).forEach(t -> doubles.add(t));
		stopWatch.stop();
		System.out.println(doubles.size());
		System.out.println(stopWatch.toString());

		Stream<String> stream = Stream.of("Java 8", "Lambdas ", "In", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);
		Stream<String> emptyStream = Stream.empty();

		int[] numbers = {2, 3, 4, 5, 6, 7};
		int sum = Arrays.stream(numbers).sum();
	}
}
