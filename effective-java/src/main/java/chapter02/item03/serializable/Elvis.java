package chapter02.item03.serializable;

public class Elvis {

	public static final Elvis INSTANCE = new Elvis();

	private Elvis() {

	}

	public void leaveTheBuilding() {
		System.out.println("Who a baby, I'm outta here!");
	}

	private Object readResolve() {
		return INSTANCE;
	}

	public static void main(String[] args) {
		Elvis elvis = Elvis.INSTANCE;
		elvis.leaveTheBuilding();
	}
}