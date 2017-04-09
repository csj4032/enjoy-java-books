package ch02;

import lombok.*;
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

		List<Apple> redApples = filterApplesByColor(inventory, "red");
		log.info("{}", redApples);

		List<Apple> heavyApples = filterApplesByWeight(inventory, 80);
		log.info("{}", heavyApples);

		List<Apple> filterApples = filterApples(inventory, "green", 80, true);
		log.info("{}", filterApples);
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

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Apple {
		private int weight;
		private String color;
	}
}
