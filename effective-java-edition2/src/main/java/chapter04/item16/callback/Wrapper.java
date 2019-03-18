package chapter04.item16.callback;

public class Wrapper implements SomethingWithCallback {

	private final WrappedObject wrappedObject;

	public Wrapper(WrappedObject wrappedObject) {
		this.wrappedObject = wrappedObject;
	}

	@Override
	public void doSomeThing() {
		wrappedObject.doSomeThing();
	}

	public void doSomethingElse() {
		System.out.println("We can do everything the wrapped object can, and more!");
	}

	@Override
	public void call() {
		System.out.println("Wrapper call");
		wrappedObject.call();
	}
}
