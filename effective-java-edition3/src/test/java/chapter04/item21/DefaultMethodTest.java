package chapter04.item21;


import org.junit.jupiter.api.Test;

public class DefaultMethodTest {

	@Test
	public void defaultMethod() {
		DefaultMethod defaultMethod = new DefaultMethodImp();
		defaultMethod.hello();
	}
}
