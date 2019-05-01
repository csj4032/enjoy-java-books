package chapter02.item01;

public class StaticFactoryMethod {

	public static StaticFactoryMethod from() {
		return new StaticFactoryMethod();
	}

	public static StaticFactoryMethod of() {
		return new StaticFactoryMethod();
	}

	private StaticFactoryMethod() {

	}


	StaticFactoryMethod(int a ) {

	}

	StaticFactoryMethod(int a,int b ) {

	}

	StaticFactoryMethod(int a,int b ,int c) {

	}
}