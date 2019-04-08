package chapter07.item42;

public class AnonymousImplement {

	public String boo(AnonymousInterface<String> anonymousInterface) {
		return anonymousInterface.foo();
	}
}
