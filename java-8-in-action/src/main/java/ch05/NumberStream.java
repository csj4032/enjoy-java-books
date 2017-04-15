package ch05;

import ch04.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ch04.LowCaloricDishes.menu;

public class NumberStream {

	public static void main(String[] args) {
		//int calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		int calories = menu.stream().mapToInt(Dish::getCalories).sum();

		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = intStream.boxed();

		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
		int max = maxCalories.orElse(1);

		IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);
	}
}
