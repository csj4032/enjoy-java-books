package chapter04.item21;

public class DefaultMethodImp implements DefaultMethod {

	@Override
	public void hello() {
		greeting();
	}

	public void greeting() {
		System.out.println("DefaultMethodImp greeting");
	}
}