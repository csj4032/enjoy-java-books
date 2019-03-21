package chapter05.item28;

import java.util.Arrays;
import java.util.List;

public class Reifiable {

	public List<?> wildCardtoArray(String[] string) {
		return Arrays.asList(string);
	}


	public <E> void varargsMethod(List<String>... varargs) {

	}
}
