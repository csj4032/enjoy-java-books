package chapter01;

public class TestShape {

	public static void main(String[] args) {
		Circle circle = new Circle(5);

		System.out.println(circle.calcArea());
	}
}

abstract class Shape {
	protected double area;

	abstract double calcArea();
}

class Circle extends Shape {

	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	double calcArea() {
		return 3.14 * (radius * radius);
	}
}
