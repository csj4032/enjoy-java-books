package chapter04.item20;

import org.junit.Test;

public class InterfaceAbstract {

	@Test
	public void method() {
		Abstract abstractSub = new AbstractSub();
		abstractSub.abstraceMethod();

		Interface interfaceImpl = new InterfaceImpl();
		interfaceImpl.defaultMethod();

		Interface anyClass = new AnyClass();
		anyClass.defaultMethod();
		((AnyClass) anyClass).abstraceMethod();

		Abstract absClass = new AnyClass();
		absClass.abstraceMethod();
		((AnyClass) absClass).defaultMethod();
	}
}