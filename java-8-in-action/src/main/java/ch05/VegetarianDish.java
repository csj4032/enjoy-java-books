package ch05;

import ch04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch04.LowCaloricDishes.menu;
import static java.util.stream.Collectors.toList;

public class VegetarianDish {

	private static final Predicate<? super Dish> dishPredicate = d -> d.getCalories() > 300;
	private static final Collector<? super Dish, ?, List<Dish>> dishListCollector = toList();

	public static void main(String[] args) {
//		List<Dish> vegetarianDishes = new ArrayList<>();
//		for (Dish d : menu) {
//			vegetarianDishes.add(d);
//		}

		List<? extends Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());
		List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());

		List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

		//List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());
		List<Dish> dishes = menu.stream().filter(dishPredicate).skip(2).collect(dishListCollector);

		List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());

		List<String> words = Arrays.asList("Java8", "Lamdbas", "In", "Action");
		List<Integer> worLengths = words.stream().map(String::length).collect(Collectors.toList());
		List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());

		List<String[]> wordList = words.stream().map(word -> word.split("")).distinct().collect(toList());
		//List<String> wordList2 = words.stream().flatMap(word -> word.split("")).distinct().collect(toList());

		String[] arrayOfWords = {"Goodbye", "World"};
		Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
		List<String> uniqueCharctors = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

		List<Integer> number1 = Arrays.asList(1, 2, 3);
		List<Integer> number2 = Arrays.asList(3, 4);
		number1.stream().map(i -> number2.stream().map(j -> new int[]{i, j})).collect(toList());
		//System.out.println(Arrays.toString(number3.get(0)));

		boolean isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);


		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findFirst();

		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));

		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		Optional<Integer> fristSquareDivisibledByThree = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
		int sum = someNumbers.stream().reduce(0, Integer::sum);
		//Optional<Integer> sum = someNumbers.stream().reduce((a, b) -> a + b);
		Optional<Integer> max = numbers.stream().reduce(Integer::max);
		Optional<Integer> min = numbers.stream().reduce(Integer::min);
	}
}