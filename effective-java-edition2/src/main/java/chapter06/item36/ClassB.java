package chapter06.item36;

public class ClassB implements InterfaceB {

	@Override
	public void method() {
		System.out.println("method1");
	}

	@Override
	public void method2() {
		System.out.println("method2");
	}

	@Override
	public void method2(String a) {

	}

	@Override
	public void method3() {

	}

	public static void main(String[] args) {
		ClassB classB = new ClassB();
		classB.method();
		classB.method2();
	}
}
