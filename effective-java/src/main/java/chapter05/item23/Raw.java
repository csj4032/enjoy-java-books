package chapter05.item23;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Raw {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings, new Integer(42));
	}

	private static void unsafeAdd(List list, Object o) {
		list.add(o);
	}

	static int rawNumElementsInCommon(Set s1, Set s2) {
		int result = 0;
		for (Object o1 : s1)
			if (s2.contains(o1))
				result++;
		return result;
	}

	static int numElementsInCommon(Set<?> s1, Set<?> s2) {
		int result = 0;
		for (Object o1 : s1)
			if (s2.contains(o1))
				result++;
		return result;
	}
}