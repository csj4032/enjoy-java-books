package ch05;

import ch03.ExecuteAround;
import ch04.Dish;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ch04.LowCaloricDishes.menu;

public class NumberStream {

	private static final boolean IS_WINDOWS = System.getProperty("os.name").contains("indow");

	public static void main(String[] args) throws URISyntaxException {
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

		//pythagoreanTriples.limit(5).forEach(t -> System.out.println(Arrays.toString(t)));
		//pythagoreanTriples2.limit(50000).forEach(t -> doubles.add(t));

		//System.out.println(doubles.size());
		//System.out.println(stopWatch.toString());

		//Stream<String> stream = Stream.of("Java 8", "Lambdas ", "In", "Action");
		//stream.map(String::toUpperCase).forEach(System.out::println);
		Stream<String> emptyStream = Stream.empty();

		int[] numbers = {2, 3, 4, 5, 6, 7};
		int sum = Arrays.stream(numbers).sum();

		ClassLoader classLoader = new ExecuteAround().getClass().getClassLoader();
		long uniqueWords = 0;
		try (Stream<String> lines = Files.lines(Paths.get(classLoader.getResource("ch05/data.txt").toURI()), Charset.defaultCharset())) {
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(uniqueWords);

		//Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
		//Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).map(t -> t[0]).forEach(System.out::println);
		Stream.generate(Math::random).limit(5).forEach(System.out::println);
	}
}
