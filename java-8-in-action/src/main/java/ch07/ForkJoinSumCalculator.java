package ch07;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private static final int THRESHOLD = 10_000;
	private final long[] number;
	private final int start;
	private final int end;

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	private ForkJoinSumCalculator(long[] number, int start, int end) {
		this.number = number;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		if (length <= THRESHOLD) {
			return computerSequentially();
		}
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(number, start, start + length / 2);
		leftTask.fork();
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(number, start + length / 2, end);
		Long rightResult = rightTask.compute();
		Long lefResult = leftTask.join();
		return lefResult + rightResult;
	}

	private long computerSequentially() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += number[i];
		}
		return sum;
	}
}