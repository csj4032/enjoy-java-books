package chapter06.item38;

import org.junit.jupiter.api.Test;

import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.Collection;

public class OperationTest {

	@Test
	public void operationTest() {
		test(BasicOperation.class, 4, 4);
		test(Arrays.asList(BasicOperation.values()), 4, 4);
	}

	private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
		for (Operation op : opEnumType.getEnumConstants())
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}

	private static <T extends Enum<T> & Operation> void test(Collection<? extends Operation> opSet, double x, double y) {
		for (Operation op : opSet)
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}
}
