package chapter04.item19;

public class ConcreteClass implements StandardInterface {

	@Override
	public void write() {

	}

	@Override
	public void read() {
		//isExisted();
		helperIsExisted();
	}

	@Override
	public boolean isExisted() {
		return false;
	}

	private boolean helperIsExisted() {
		return false;
	}
}