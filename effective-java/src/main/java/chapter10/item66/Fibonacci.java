package chapter10.item66;

import lombok.experimental.var;
import lombok.val;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

	final int n;

	Fibonacci(int n) {
		val a = "a";
		//final String a = "a";



		this.n = n;
	}

	protected Integer compute() {
		if (n <= 1)
			return n;
		Fibonacci f1 = new Fibonacci(n - 1);
		f1.fork();
		Fibonacci f2 = new Fibonacci(n - 2);
		return f2.compute() + f1.join();
	}

	protected Integer recursive(int n) {
		if (n <= 1) return n;
		return recursive(n - 1) + recursive(n - 2);
	}

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci(41);
		ForkJoinPool pool = new ForkJoinPool();
		Long start = System.currentTimeMillis();
		System.out.println(pool.invoke(fibonacci));
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		System.out.println(fibonacci.recursive(41));
		System.out.println(System.currentTimeMillis() - start);
	}
}