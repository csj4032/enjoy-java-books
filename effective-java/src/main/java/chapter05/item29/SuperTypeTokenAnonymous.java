package chapter05.item29;

import chapter06.item33.Herb;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeTokenAnonymous {

	static class Sup<T> {
		T value;
	}


	public static void main(String[] args) throws NoSuchFieldException {

		// Local class
		class Sub extends Sup<String> {

		}
		Sub lc = new Sub();

		// Anonymous class
		Sup ac = new Sup<String>() {};

		Type t = (new Sup<List<String>>() {}).getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) t;
		System.out.println(pType.getActualTypeArguments()[0]);
	}
}
