package ch06;

import ch04.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static ch04.LowCaloricDishes.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Partition {

	public static void main(String[] args) {
		Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
		//System.out.println(partitionedMenu);
		List<Dish> vegetarianDishes = partitionedMenu.get(true);
		//System.out.println(vegetarianDishes);

		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
		//System.out.println(vegetarianDishesByType);

		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
		//System.out.println(mostCaloricPartitionedByVegetarian);

		//System.out.println(isPrime(11));
		System.out.println(partitionPrimes(11));
	}

	public static boolean isPrime(int candidate) {
		//return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}

	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
	}
}
