package ch02;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorApples {

	public static void main(String[] args) {

		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

//		inventory.sort(new Comparator<Apple>() {
//			@Override
//			public int compare(Apple o1, Apple o2) {
//				return o1.getWeight().compareTo(o2.getWeight());
//			}
//		});

		inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
		inventory.sort(Comparator.comparing(Apple::getWeight));
		Collections.sort(inventory, Comparator.comparing(Apple::getColor).thenComparing(Apple::getWeight));

		System.out.println(inventory);

		System.out.println(inventory);
	}
}