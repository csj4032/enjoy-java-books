package chapter05.item27;

public class GenericSingletonFactory {

	private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
		public Object apply(Object arg) {
			return arg;
		}
	};

	private static UnaryFunction<Object> IDENTITY_FUNCTION2 = (arg) -> arg;

	@SuppressWarnings("unchecked")
	public static <T> UnaryFunction<T> identityFunction() {
		return (UnaryFunction<T>) IDENTITY_FUNCTION;
	}

	@SuppressWarnings("unchecked")
	public static <T> UnaryFunction<T> identityFunction2() {
		return (UnaryFunction<T>) IDENTITY_FUNCTION2;
	}

	public static void main(String[] args) {

		UnaryFunction<Object> aa = null;
		UnaryFunction<String> bb = null;

		// 컴파일 시점
		// aa = (UnaryFunction<Object>) bb;

		String[] strings = {"jute", "hemp", "nylon"};
		UnaryFunction<String> sameString = new UnaryFunction<String>() {
			@Override
			public String apply(String arg) {
				return arg;
			}
		};
		for (String s : strings) System.out.println(sameString.apply(s));

		String[] strings2 = {"jute", "hemp", "nylon"};
		UnaryFunction<String> sameString2 = (arg) -> arg;
		for (String s : strings2) System.out.println(sameString2.apply(s));

		// 실제 런타임 시점에 identityFunction 작동 (Erasure)
		Number[] numbers = {1, 2.0, 3L};
		UnaryFunction<Number> sameNumber = identityFunction();
		for (Number n : numbers) System.out.println(sameNumber.apply(n));

		Number[] numbers2 = {1, 2.0, 3L};
		UnaryFunction<Number> sameNumber2 = identityFunction2();
		for (Number n : numbers2) System.out.println(sameNumber2.apply(n));
	}
}
