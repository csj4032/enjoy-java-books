package bigO;

public class AdditionExample1 {

	int product(int a, int b) {
		int sum = 0;
		for (int i = 0; i < b; i++) {
			sum += a;
		}
		return sum;
	}

	public static void main(String[] args) {
		AdditionExample1 additionExample1 = new AdditionExample1();
		additionExample1.product(1, 2);
	}
}