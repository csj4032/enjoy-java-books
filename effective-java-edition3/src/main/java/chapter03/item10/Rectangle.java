package chapter03.item10;

public class Rectangle extends Shape {
	private int height;
	private int width;

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Shape))
			return false;
		Rectangle rectangle = (Rectangle) o;
		return rectangle.height == this.height && rectangle.width == this.width;
	}
}
