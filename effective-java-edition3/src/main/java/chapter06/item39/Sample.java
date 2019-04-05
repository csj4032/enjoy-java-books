package chapter06.item39;

public class Sample {

	@Tester
	public static void m1() {
	}

	public static void m2() {
	}

	@Tester
	public static void m3() {
		throw new RuntimeException("Boom");
	}

	public static void m4() {
	}

	@Tester
	public void m5() {
	}

	public static void m6() {
	}

	@Tester
	public static void m7() {
		throw new RuntimeException("Crash");
	}

	public static void m8() {
	}
}
