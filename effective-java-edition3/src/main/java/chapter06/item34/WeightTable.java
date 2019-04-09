package chapter06.item34;

public class WeightTable {

	public void tablePrint(double earthWeight) {
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for (Planet p : Planet.values()) {
			System.out.printf("%s에서의 무게는 %f이다.%n", p, p.surfaceWeight(mass));
		}
	}

	public static Operation inverse(Operation op) {
		return switch (op) {
			case PLUS -> Operation.MINUS;
			case MINUS -> Operation.PLUS;
			case TIMES -> Operation.DIVIDE;
			case DIVIDE -> Operation.TIMES;
			default -> throw new AssertionError("알 수 없는 연산: " + op);
		};
	}
}
