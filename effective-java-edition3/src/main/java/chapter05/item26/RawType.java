package chapter05.item26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RawType {

	public void rawType() {
		List rawList = new ArrayList();

		List<Object> objects = new ArrayList<>();
		objects.add("!");
		objects.add(1);

		List<String[]> strings = new ArrayList<>();
		strings.add(new String[]{"a"});
		System.out.println(strings);

		List<int[]> ints = new ArrayList<>();
		ints.add(new int[]{1});
		System.out.println(ints);

		List clazz = new ArrayList();
		clazz.add(int.class);
		clazz.add(int[].class);
		clazz.add(void.class);
		//clazz.add(List<String>.class);
		//clazz.add(List<?>.class);
		System.out.println(clazz);

		Class<int[]> intClass = int[].class;
		Class<Long> longClass = long.class;
		Class<List> listClass = List.class;
		//Class<List<String>> listStringClass = List<String>.class;
		//Class<List<?>> listWildClass = List<?>.class;

		Class<List<String>> clazzz;
	}
}
