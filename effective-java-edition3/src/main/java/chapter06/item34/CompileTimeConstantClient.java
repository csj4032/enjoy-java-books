package chapter06.item34;

public class CompileTimeConstantClient {

	private int a;
	private int b;
	private int c;
	private int d;

	private static int plus(String domain) {
		if ("www.amazon.com".equals(domain)) {
			int a = CompileTimeConstant.APPLE_FUJI;
			int b = CompileTimeConstant.APPLE_PIPPIN;
			return a + b;
		}
		return 0;
	}

	public static void main(String[] args) {
		plus("www.google.com");
	}
}