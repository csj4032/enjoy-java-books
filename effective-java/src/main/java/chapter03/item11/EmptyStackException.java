package chapter03.item11;

public class EmptyStackException extends IllegalStateException {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		//EmptyStackException emptyStackException = (EmptyStackException) Class.forName(EmptyStackException.class.toString()).newInstance();
		//emptyStackException.aa();
		EmptyStackException emptyStackException = new EmptyStackException();
	}
}