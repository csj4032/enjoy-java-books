package ch05;

import ch04.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static ch04.LowCaloricDishes.menu;

public class VegetarianDish {

	public static void main(String[] args) {
//		List<Dish> vegetarianDishes = new ArrayList<>();
//		for (Dish d : menu) {
//			vegetarianDishes.add(d);
//		}

		List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
	}
}