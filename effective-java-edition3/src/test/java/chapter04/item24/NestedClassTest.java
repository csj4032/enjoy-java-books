package chapter04.item24;

import org.junit.jupiter.api.Test;

public class NestedClassTest {

	@Test
	public void nestedClass() {
		var staticMemberClass = NestedClass.StaticMemeberClass.STATIC_MEMBER_CLASS;
		var nonStaticMemberClass = new NestedClass().new NonStaticMemberClass().getName();
		var nonStaticMemberClass2 = new NestedClass().getNonStaticMemberClass();
		var iterator = new NestedClass().anonymousClass();
		new NestedClass().localClass();
	}
}