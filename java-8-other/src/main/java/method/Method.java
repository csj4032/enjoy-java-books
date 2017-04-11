package method;

public class Method {

	{
		System.out.println("Instance Field : " + Method.class.getName());
	}

	void whoIs() {
		System.out.println(Method.class.getName());
	}

	public static void main(String[] args) {
		Method method1 = new Method();
		Method method2 = new Method();
	}
}
