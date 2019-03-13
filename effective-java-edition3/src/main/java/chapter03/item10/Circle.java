package chapter03.item10;

public class Circle extends Shape {

	private int radius;

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Shape))
			return false;
		Circle circle = (Circle) o;
		return circle.radius == this.radius;
	}
}
