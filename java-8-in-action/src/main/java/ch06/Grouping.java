package ch06;

import ch04.CaloricLevel;
import ch04.Dish;

import java.util.*;

import static ch04.LowCaloricDishes.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Grouping {

	public static void main(String[] args) {
		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		//System.out.println(dishesByType);

		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
			return getCaloricLevel(dish);
		}));
		//System.out.println(dishesByCaloricLevel);

		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
			return getCaloricLevel(dish);
		})));
		//System.out.println(dishesByTypeCaloricLevel);

		Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
		//System.out.println(typesCount);

		//Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, maxBy(comparing(Dish::getCalories))));
		//System.out.println(mostCaloricByType);

		Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
		//System.out.println(mostCaloricByType);

		Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
		//System.out.println(totalCaloriesByType);

		//Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
		//	return getCaloricLevel(dish);
		//}, toSet())));
		//System.out.println(caloricLevelsByType);

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
			return getCaloricLevel(dish);
		}, toCollection(HashSet::new))));
		System.out.println(caloricLevelsByType);
	}

	private static CaloricLevel getCaloricLevel(Dish dish) {
		if (dish.getCalories() <= 400) {
			return CaloricLevel.DIET;
		} else if (dish.getCalories() <= 700) {
			return CaloricLevel.NORMAL;
		} else {
			return CaloricLevel.FAT;
		}
	}
}
