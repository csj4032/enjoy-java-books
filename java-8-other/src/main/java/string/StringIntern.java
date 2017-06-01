package string;

import java.util.HashMap;
import java.util.Map;

public class StringIntern {

	public static void main(String[] args) {
		String str1 = "Hello";
		String str2 = new String("Hello").intern();
		String str3 = "Hello";

		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));

		str1 = "Hello1";

		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));

		String a = "hello";
		String b = "hello";

		String s3 = new String("Hello");
		String s4 = new String("Hello").intern();
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4));

		validate("1", 1, 3);
	}


	public enum RefundRoleType {
		A, B, C
	}

	private static final Map<String, String> refundRoleItems = new HashMap<>();

	public static String validate(String string, Integer... integers) {
		for (int i = 0, n = integers.length; i < n; i++) {
			return refundRoleItems.get(integers[i]);
		}
		return "";

	}
}
