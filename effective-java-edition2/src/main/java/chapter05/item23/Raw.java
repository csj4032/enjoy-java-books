package chapter05.item23;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Raw {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, new Integer(42));
		String s = strings.get(0);
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
		// s1.add(new Integer(1)); page 155 Collection<?>에는 null 이외의 어떤 원소도 넣을 수 없다.
		// s1.add(null); page 155 Collection<?>에는 null 이외의 어떤 원소도 넣을 수 없다.
		// for (Integer o1 : s1) page 155 어떤 자료형의 객체를 꺼낼 수 있는지도 알 수 없다.
		for (Object o1 : s1)
			if (s2.contains(o1))
				result++;
		return result;
	}
}