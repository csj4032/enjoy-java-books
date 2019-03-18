package chapter04;

import chapter04.item15.Bar;
import chapter04.item15.Foo;
import chapter04.item15.Thing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

public class Car extends Bar {

	private static final Thing[] PRIVATE_VALUES = {};
	public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

	public static final Thing[] values() {
		return PRIVATE_VALUES.clone();
	}


	static String[] arrayString = {"a", "b", "c"};
	static List<String> strings = Arrays.asList(arrayString);


	static IntFunction<String[]> k = (e) -> new String[e];

	public static void main(String[] args) {
		Foo foo = new Bar();
		Bar bar = new Bar();

		((Bar) foo).getName();

		Object[] array = strings.toArray();
		String[] array2 = strings.toArray(new String[0]);
		String[] array3 = strings.toArray(new String[strings.size()]);
		String[] array4 = strings.toArray(String[]::new); // 11
		String[] array5 = strings.toArray(k);

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));
		System.out.println(Arrays.toString(array5));
	}
}