package chapter02.item01;

import java.util.HashMap;
import java.util.Map;

public class Java89 {

	public static void main(String[] args) {
		Java8StaticMethod.MemberClass java8StaticMethodMemberClass = new Java8StaticMethod.MemberClass();
		Java9StaticMethod.MemberClass java9StaticMethodMemberClass = new Java9StaticMethod.MemberClass();

		int a = 0;
		Integer b = 1;
		b = a;

		Map<String, Object> map = new HashMap();
		map.put("a", "a");
	}
}