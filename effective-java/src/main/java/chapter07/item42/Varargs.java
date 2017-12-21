package chapter07.item42;

import java.util.Arrays;

public class Varargs {

	static int sum(int... args) {
		int sum = 0;
		for (int arg : args)
			sum += arg;
		return sum;
	}

	static int min(int firstArg, int... remainingArgs) {
		int min = firstArg;
		for (int arg : remainingArgs)
			if (arg < min)
				min = arg;
		return min;
	}

	public static void main(String[] args) {

		int [][] a = {{1},{2},{3},{4}};
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.deepToString(a));

		System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		System.out.println(min(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
	}
}
