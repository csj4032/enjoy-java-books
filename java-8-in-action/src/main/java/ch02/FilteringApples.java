package ch02;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FilteringApples {

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

		List<Apple> greenApples = filterGreenApples(inventory);
		log.info("{}", greenApples);

//		List<Apple> redApples = filterApplesByColor(inventory, "red");
//		List<Apple> redApples = filter(inventory, new ApplePredicate() {
//			@Override
//			public boolean test(Apple apple) {
//				return "red".equals(apple.getColor());
//			}
//		});

		List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
		log.info("{}", redApples);

		List<Apple> heavyApples = filterApplesByWeight(inventory, 80);
		log.info("{}", heavyApples);

		List<Apple> filterApples = filterApples(inventory, "green", 80, true);
		log.info("{}", filterApples);

		List<Apple> redAndHeavyApple = filter(inventory, new AppleRedAndHeavyPredicate());
		log.info("{}", redAndHeavyApple);

	}

	static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) result.add(apple);
		}
		return result;
	}

	static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (color.equals(apple.getColor())) result.add(apple);
		}
		return result;
	}

	static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) if (weight == apple.getWeight()) result.add(apple);
		return result;
	}

	static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) if (flag && apple.getColor().equals(color) && apple.getWeight() > weight) result.add(apple);
		return result;
	}

	static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : inventory) {
			if (p.test(e)) result.add(e);
		}
		return result;
	}

	static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) result.add(apple);
		}
		return result;
	}

	public interface Predicate<T> {
		boolean test(T t);
	}

	public interface ApplePredicate {
		boolean test(Apple apple);
	}

	static class AppleHeavyWeightPredicate<T> implements ApplePredicate {
		@Override
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleGreenColorPredicate<T> implements ApplePredicate {
		@Override
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		@Override
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 110;
		}
	}
}
