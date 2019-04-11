package bigO;

public class Example13 {

	static int count;

	int fib(int n) {
		count++;
		if (n <= 0) return 0;
		//else if (n == 1) return 1;
		return fib(n - 1) + fib(n - 1);
	}

	public static void main(String[] args) {
		Example13 example = new Example13();
		System.out.println(example.fib(3));
		System.out.println(count);
	}
}