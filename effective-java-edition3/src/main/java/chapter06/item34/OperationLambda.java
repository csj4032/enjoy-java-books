package chapter06.item34;

import java.util.function.Function;
import java.util.function.Supplier;

public enum OperationLambda implements Function<Supplier<Double>, Double> {

	OPERATION() {
		@Override
		public Double apply(Supplier<Double> supplier) {
			return supplier.get();
		}
	};

	public static void main(String[] args) {
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		System.out.println(OperationLambda.OPERATION.apply(() -> x + y));
		System.out.println(OperationLambda.OPERATION.apply(() -> x - y));
		System.out.println(OperationLambda.OPERATION.apply(() -> x * y));
		System.out.println(OperationLambda.OPERATION.apply(() -> x / y));
	}
}