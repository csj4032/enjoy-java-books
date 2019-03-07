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
}