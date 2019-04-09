package chapter06.item37;

import java.util.Stack;

public class Offset implements Cloneable {

	private int x;
	private int y;

	public Offset(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public Offset clone() {
		return new Offset(this.getX(), this.getY());
	}

	@Override
	public String toString() {
		return "Offset{" + "x=" + x + ", y=" + y + "}";
	}

	public static void main(String[] args) {
		Stack<Offset> stack = new Stack<>();
		stack.add(new Offset(1, 1));
		stack.push(new Offset(2, 2));
		Offset cur = stack.pop();
		Offset cur3 = cur.clone();
		Offset cur2 = new Offset(cur.getX(), cur.getY());
		cur.setX(10);
		System.out.println(cur2);
		System.out.println(cur3);
	}
}
