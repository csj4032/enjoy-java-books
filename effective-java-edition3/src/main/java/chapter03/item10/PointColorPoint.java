package chapter03.item10;

import java.awt.*;

public class PointColorPoint {

	public static void main(String[] args) {
		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1, 2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

		SmellPoint p4 = new SmellPoint(1, 2);

		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
		System.out.println(p1.equals(p3));

		// StackOverflowError
		p1.equals(p4);
	}
}