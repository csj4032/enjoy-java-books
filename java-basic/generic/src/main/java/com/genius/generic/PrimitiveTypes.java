package com.genius.generic;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveTypes {

	public void primitiveType() {
		//List<int> integers = new ArrayList();
		//List<double> integers = new ArrayList();
		List<int[]> integerArray = new ArrayList();
		integerArray.add(new int[]{1, 2, 3});
		integerArray.add(new int[]{1, 2, 3});

		for (int[] ints : integerArray) {

		}

		//List<class> classes = new ArrayList<>();
		List classes = new ArrayList<>();
		classes.add(String.class);
		classes.add(List.class);
		classes.add(String[].class);
		classes.add(int.class);

		//classes.add(List<String>.class);
		//classes.add(List<?>.class);
	}
}