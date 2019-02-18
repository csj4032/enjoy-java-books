package chapter02.item01;

public interface Java9StaticMethod {

	String STATIC_FIELD = "JAVA_9";

	class MemberClass {
		public MemberClass() {

		}
	}

	default void print(String str) {
		if (isNull(str))
			System.out.println(str);
	}

	// 자바9에서는 private 정적 메서드까지 허락
	private static boolean isNull(String str) {
		return str == null ? true : "".equals(str) ? true : false;
	}
}