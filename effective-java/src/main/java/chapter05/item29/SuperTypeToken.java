package chapter05.item29;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SuperTypeToken {

	static class Sup<T> {
		T value;
	}

	//reification
	static class Sub extends Sup<String> {

	}

	public static void main(String[] args) throws NoSuchFieldException {
		Sup<String> s = new Sup();
		System.out.println(s.getClass().getDeclaredField("value").getType());

		Sup b = new Sub();
		Type t = b.getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) t;
		System.out.println(pType.getActualTypeArguments()[0]);
	}
}
