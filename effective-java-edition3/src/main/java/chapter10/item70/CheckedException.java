package chapter10.item70;

public class CheckedException {

	public void checked(int value) throws Exception {
		if (value > 10) {
			throw new Exception();
		}
	}
}
