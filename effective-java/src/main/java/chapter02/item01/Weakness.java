package chapter02.item01;

public class Weakness {

	public static Weakness newInstance() {
		return new Weakness();
	}

	private Weakness() {

	}
}

// https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html
// https://docs.oracle.com/javase/tutorial/java/IandI/super.html
//class WeaknessSub extends Weakness {
//
//	private WeaknessSub() {
//		super();
//	}
//}