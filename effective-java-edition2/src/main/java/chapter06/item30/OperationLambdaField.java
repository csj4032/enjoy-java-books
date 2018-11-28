package chapter06.item30;

import java.util.function.BiFunction;

public enum OperationLambdaField implements BiFunction<Double, Double, Double> {

	PLUS((a, b) -> a + b),
	MINUS((a, b) -> a - b),
	TIMES((a, b) -> a + b),
	DIVIDE((a, b) -> a + b);

	private final BiFunction<Double, Double, Double> biFunction;

	OperationLambdaField(BiFunction<Double, Double, Double> biFunction) {
		this.biFunction = biFunction;
	}

	@Override
	public Double apply(Double d1, Double d2) {
		return biFunction.apply(d1, d2);
	}

	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		System.out.println(OperationLambdaField.PLUS.apply(x,y));
		System.out.println(OperationLambdaField.MINUS.apply(x,y));
		System.out.println(OperationLambdaField.TIMES.apply(x,y));
		System.out.println(OperationLambdaField.DIVIDE.apply(x,y));
	}
}