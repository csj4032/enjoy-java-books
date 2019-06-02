package chapter05.item26;

//https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.5
public class CovariantOverriding {

	public static void main(String[] args) {
		CovariantObj c = new SubCovariantObj();
		System.out.println(c.getObject().x);
	}
}

class A {
	int x = 5;
}

class B extends A {
	int x = 6;
}

class CovariantObj {
	public A getObject() {
		System.out.println("In A");
		return new A();
	}
}

class SubCovariantObj extends CovariantObj {
	@Override
	public B getObject() {
		System.out.println("In B");
		return new B();
	}
}
