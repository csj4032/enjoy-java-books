package chapter07.item42;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LambdaTest {

	private static class PrivateStaticAnonymousInterface {
		public static AnonymousInterface getAnonymous() {
			return new AnonymousInterface<>() {
				@Override
				public String foo() {
					System.out.println(this.getClass().getName());
					return "booFooPrivateStatic";
				}
			};
		}
	}

	@Test
	public void lambdaTest() {
		AnonymousImplement anonymousImplement = new AnonymousImplement();
		String booFoo = anonymousImplement.boo(new AnonymousInterface<>() {
			@Override
			public String foo() {
				// 함수 객체가 자신을 참조해야 한다면 반드시 익명 클래스
				System.out.println(this.getClass().getName());
				return "foo";
			}
		});

		String booFooLambda = anonymousImplement.boo(() -> {
			// 람다는 자신 을 참조할 수 없다.
			System.out.println(this.getClass().getName());
			return "fooLambda";
		});

		String booFooPrivateStatic = anonymousImplement.boo(LambdaTest.PrivateStaticAnonymousInterface.getAnonymous());

		Assertions.assertEquals("foo", booFoo);
		Assertions.assertEquals("fooLambda", booFooLambda);
		Assertions.assertEquals("booFooPrivateStatic", booFooPrivateStatic);

		AbstractImplement abstractImplement = new AbstractImplement();
		String afoo = abstractImplement.boo(new AbstractClass() {
			@Override
			String foo() {
				return "afoo";
			}
		});

		Assertions.assertEquals("afoo", afoo);
	}
}
