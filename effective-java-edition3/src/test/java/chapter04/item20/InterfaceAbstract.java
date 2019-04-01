package chapter04.item20;

import org.junit.jupiter.api.Test;

public class InterfaceAbstract {

	@Test
	public void method() {
		Abstract abstractSub = new AbstractSub();
		abstractSub.abstractMethod();

		Interface interfaceImpl = new InterfaceImpl();
		interfaceImpl.defaultMethod();
	}
}