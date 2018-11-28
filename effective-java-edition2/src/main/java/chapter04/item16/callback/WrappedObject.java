package chapter04.item16.callback;

public class WrappedObject implements SomethingWithCallback {

	private final Service service;

	public WrappedObject(Service service) {
		this.service = service;
	}

	@Override
	public void doSomeThing() {
		// 자기 참조를 전달, 역호출 과정에사 포장 객체(Wrapper)는 제외
		service.performAsync(this);
	}

	@Override
	public void call() {
		System.out.println("WrappedObject callback");
	}
}