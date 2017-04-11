package ch03;

import ch02.Apple;
import ch02.FilteringApples;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import com.sun.xml.internal.ws.util.StreamUtils;

import java.util.*;
import java.util.concurrent.Future;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class MethodReference {

	private static Comparator<Apple> c = Comparator.comparing((a) -> a.getWeight());

	private static Comparator<Apple> d = (o1, o2) -> o1.getWeight().compareTo(o2.getWeight());

	// 코드전달
	public static class AppleComparator implements Comparator<Apple> {
		@Override
		public int compare(Apple o1, Apple o2) {
			return o1.getWeight().compareTo(o2.getWeight());
		}
	}

	public static class Letter {

		public static String addHeader(String text) {
			return "From Raoul, Mario And Alan: " + text;
		}

		public static String addFooter(String text) {
			return text + " Kind regard";
		}

		public static String checkSpelling(String text) {
			return text.replaceAll("labda", "lambda");
		}
	}

	public double integrate(DoubleFunction<Double> f, double a, double b) {
		return f.apply(a) + f.apply(b) * (b - a) / 2.0;
	}


	public static void main(String[] args) {
		List<Apple> inventory = Lists.newArrayList(Apple.builder().color("A").weight(120).build(), Apple.builder().color("B").weight(110).build());
		inventory.sort(new AppleComparator());

		// 익명클래스
		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getWeight().compareTo(o2.getWeight());
			}
		});

		inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

		inventory.sort(d);

		inventory.sort(Comparator.comparing(Apple::getWeight));

		// 람다 표현식
		inventory.sort(Comparator.comparing((a) -> a.getWeight()));

		inventory.sort(c);

		// 메서드 레퍼런스
		inventory.sort(comparing(Apple::getWeight));

		// 역정렬
		inventory.sort(comparing(Apple::getWeight).reversed());

		// Comparator
		inventory.sort(comparing(Apple::getWeight).reversed());

		Predicate<Apple> redApple = (i) -> i.getWeight().equals("read");
		Predicate<Apple> notRedApple = redApple.negate();
		Predicate<Apple> redAppleAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()));

		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addHeader);

		List<Apple> list = Arrays.asList(
				Apple.builder().color("A").weight(200).build(),
				Apple.builder().color("A").weight(210).build(),
				Apple.builder().color("A").weight(210).build(),
				Apple.builder().color("D").weight(210).build(),
				Apple.builder().color("C").weight(210).build(),
				Apple.builder().color("B").weight(330).build());
		list.stream().collect(Collectors.partitioningBy(a -> a.getColor().equals("A")));

		List<Apple> apples = Stream.iterate(0, n -> n + 1).limit(list.size()).map(i -> {
			Apple originalApple = list.get(i);
			Apple apple = Apple.builder().color(originalApple.getColor() + "B").weight(i).build();
			return apple;
		}).limit(5).collect(toList());
		System.out.println(apples);
	}
}
