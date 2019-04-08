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
				System.out.println(this.getClass().getName());
				return "foo";
			}
		});

		String booFooLambda = anonymousImplement.boo(() -> {
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
