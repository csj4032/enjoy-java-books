package chapter07.item44;

import java.util.Arrays;

public class BasicFunction {

	public void intFunction() {

		//IntPredicate
		int[] integers = new int[]{1, 2, 3};
		Arrays.stream(integers).filter(x -> x > 10);

		// LongBinaryOperator
		long[] longs = new long[]{1l, 2l, 3l, 4l};
		Arrays.stream(longs).reduce(0l, (l1, l2) -> l2);

		// LongFunction
		Arrays.stream(integers).mapToObj(e -> new long[e]);

		// LongToIntFunction
		Arrays.stream(longs).mapToInt(e -> (int) e);

		// ToLongFunction
		int[][] deepIntegers = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		Arrays.stream(deepIntegers).mapToLong(e -> e.length);
	}
}
