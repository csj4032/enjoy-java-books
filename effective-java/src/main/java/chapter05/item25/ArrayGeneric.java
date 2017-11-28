package chapter05.item25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayGeneric {

	public static void main(String[] args) {

		/**
		 E, List<E>, List<String>와 같은 자료형은 실체화 불기능(non-reHable) 자료형으로 알려져 있다 ULS, 4.7]. 쉽게 말하자면, 프로그램이 실행될 때 해당 자료형을 표현하는 정보의 양이 컴파일 시점에 필요한 정보의 양보다 적은 자료형이 실체화 불기능 자료형이다.'
		 실체화 가능한 형인자 자료형은 빠t(?)나 MaP(?,?) 같은 비한정적 와일드카드 자료형(unbounded wildcald type)뿐이다. 쓸 일이 별로 없긴 하지만, 비한정적 와일드카드 자료형의 배열은 문법상 허용된다.
		 **/

		List<String> strings = new ArrayList<>();

		List<?>[] l1 = new List<?>[2];
		l1[0] = Arrays.asList("one", "two", "tree");
		l1[1] = Arrays.asList(1, 2, 3);
		refiable(l1);

		// 컴파일 불가능
		//List<String>[] l2 = new ArrayList<String>[1];
		//nonRefiable(l2);

		//varargs(Arrays.asList("가"));
	}

	private static void refiable(List<?>[] l1) {
		System.out.println(l1[0]);
		System.out.println(l1[1]);

		//l1[0].add("a");
		List<String> l2 = (List<String>) l1[1];
		System.out.println(l2.get(0));
	}

	private static void nonRefiable(List<String>[] l1) {

	}

	@SafeVarargs
	private static void varargs(List<String>... stringLists) {
		Object[] array = stringLists;
		List<Integer> tmpList = Arrays.asList(42);
		array[0] = tmpList;
		String s = stringLists[0].get(0);
	}
}