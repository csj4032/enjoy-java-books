package chapter06.item38;

import org.junit.jupiter.api.Test;

public class OperationTest {

	@Test
	public void operationTest() {
		test(ExtendedOperation.class, 4, 4);
	}

	private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
		for (Operation op : opEnumType.getEnumConstants())
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}
}
