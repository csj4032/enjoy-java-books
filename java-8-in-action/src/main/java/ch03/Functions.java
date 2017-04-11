package ch03;

import ch02.Apple;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class Functions {

	public static <T> List<T> filter(List<T> list, java.util.function.Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T t : result) {
			if (p.test(t)) {
				result.add(t);
			}
		}
		return result;
	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T t : list) result.add(f.apply(t));
		return result;
	}

	public static void main(String[] args) {
		log.info("{}", filter(Arrays.asList(1, 2, 3, 4, 5, 6, 7), (i) -> i > 6));
		forEach(Arrays.asList(1, 2, 3, 4, 5, 6, 7), System.out::print);
		map(Arrays.asList(1, 2, 3, 4, 5, 6, 7), (i) -> String.valueOf(i));

		Supplier s = Apple::new;
		s.get();

		Function<Integer, Apple> c2 = new Function<Integer, Apple>() {
			@Override
			public Apple apply(Integer integer) {
				return Apple.builder().weight(integer).color("").build();
			}
		};
		Apple a2 = c2.apply(100);
		System.out.println(a2);
	}
}
