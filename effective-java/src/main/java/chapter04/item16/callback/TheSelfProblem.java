package chapter04.item16.callback;

public class TheSelfProblem {

	public static void main(String[] args) {
		Service service = new Service();
		WrappedObject wrappedObject = new WrappedObject(service);
		Wrapper wrapper = new Wrapper(wrappedObject);
		wrapper.doSomeThing();
	}
}