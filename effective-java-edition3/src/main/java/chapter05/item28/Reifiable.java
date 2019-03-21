package chapter05.item28;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Reifiable {

	private static void reifiable() {
		List<String>[] stringLists = new ArrayList[1];
		List<Integer> intList = List.of(42);
		Object[] objects = stringLists;
		objects[0] = intList;
		String s = stringLists[0].get(0);
	}

	public List<?> wildCardtoArray(String[] string) {
		return Arrays.asList(string);
	}

	//Warning:(17, 51) java: Possible heap pollution from parameterized vararg type E
	//제네릭 타입과 가변인수 메서드(varargs method, 아이템 53)를 함께 쓰면 해석하기 어려운 경고 메시지를 받게 된다.
	@SafeVarargs
	public static <E> void varargsMethod(E... varargs) {
		//가변인수 메서드 를 호출할 때마다 가변인수 매개변수를 담을 배열이 하나 만들어지는데, 이때 그 배열의 원소가 실체화 불가 타입이라면 경고가 발생하는 것이다
		//Warning:(18, 39) java: Varargs method could cause heap pollution from non-reifiable varargs parameter varargs
		E[] list = varargs;
		log.info("length : {}", list.length);
	}

	//배열로 형변환할 때 제네릭 배열 생성 오류나 비검사 형변환 경고가 뜨는 경 우 대부분은 배열인 E[] 대신 컬렉션인 List<E>를 사용하면 해결된다.
	public static <E> void varargsGenericMethod(List<E> varargs) {
		List<E> list = varargs;
		log.info("list : " + list);
	}
}