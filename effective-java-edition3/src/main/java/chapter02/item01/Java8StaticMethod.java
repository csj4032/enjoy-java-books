package chapter02.item01;

public interface Java8StaticMethod {

	String STATIC_FIELD = "JAVA_8";

	class MemberClass {
		public MemberClass() {

		}
	}

	default void print(String str) {
		if (isNull(str))
			System.out.println(str);
	}

	// 자바 8에서도 인터페이스에는 public 정적 멤버만 허용
	static boolean isNull(String str) {
		return str == null ? true : "".equals(str) ? true : false;
	}
}