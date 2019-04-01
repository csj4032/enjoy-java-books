package chapter06.item34;

public class CompileTimeConstantClient {

	public static void main(String[] args) {

		int a = CompileTimeConstant.APPLE_FUJI;
		int b = CompileTimeConstant.APPLE_PIPPIN;

		System.out.println(a);
		System.out.println(b);
	}
}