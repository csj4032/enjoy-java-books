package ch04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LowCaloricDishes {

	public static List<Dish> menu = new ArrayList();

	static {
		menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 400, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));
	}

	public static void main(String[] args) {
//		List<String> threeHighCaloriesDishName = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3).collect(toList());
//		System.out.println(threeHighCaloriesDishName);

//		List<String> names = new ArrayList<>();
//		for (Dish d : menu) {
//			names.add(d.getName());
//		}

//		Iterator<Dish> iterator = menu.iterator();
//		while (iterator.hasNext()) {
//			Dish d = iterator.next();
//			names.add(d.getName());
//		}

//		List<String> names = menu.stream().map(Dish::getName).collect(Collectors.toList());

		List<String> names = menu.stream().filter(d -> {
			System.out.println("filtering " + d.getName());
			return d.getCalories() > 300;
		}).map(d -> {
			System.out.println("mapping " + d.getName());
			return d.getName();
		}).limit(3).collect(Collectors.toList());

		System.out.println(names);
	}
}