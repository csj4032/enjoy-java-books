package chapter05;

import chapter05.item33.AbstractClass;

import java.util.List;

public class AbstractSubClass {

	public AbstractSubClass() {
		System.out.println("AbstractClass Sub Class");
	}

	public static void main(String[] args) {
		//AbstractSubClass abstractSubClass = new AbstractSubClass();
		AbstractClass abstractClass = new AbstractClass() {
			@Override
			public String method() {
				return "";
			}
		};
		abstractClass.method();
		List d;
	}
}
