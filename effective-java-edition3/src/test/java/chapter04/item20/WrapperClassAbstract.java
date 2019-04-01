package chapter04.item20;


import org.junit.jupiter.api.Test;

public class WrapperClassAbstract {

	@Test
	public void wrapper() {
		AbstractSub abstractSub = new AbstractSub();
		WrapperClass wrapperClass = new WrapperClass(abstractSub);
		wrapperClass.fooMethod();

		InterfaceImplExtend interfaceImplExtend = new InterfaceImplExtend();

	}
}
