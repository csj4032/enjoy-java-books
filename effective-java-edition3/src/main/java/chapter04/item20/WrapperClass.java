package chapter04.item20;

public class WrapperClass {

	private Abstract anAbstract;

	public WrapperClass(Abstract anAbstract) {
		this.anAbstract = anAbstract;
	}

	public void fooMethod() {
		this.anAbstract.abstractMethod();
	}
}