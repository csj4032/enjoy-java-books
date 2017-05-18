package interfaceMethod;

public interface InterfaceMethod {

	default void print(String str) {
		if (!isNull(str))
			System.out.println("MyData Print::" + str);
	}

	static boolean isNull(String str) {
		System.out.println("Interface Null Check");
		return str == null ? true : "".equals(str) ? true : false;
	}

	static void main(String[] args) {
		InterfaceMethod interfaceMethod = new InterfaceClass();
		interfaceMethod.print("Hello");
	}
}

class InterfaceClass implements InterfaceMethod {

}