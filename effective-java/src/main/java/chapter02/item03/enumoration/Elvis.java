package chapter02.item03.enumoration;

public enum Elvis {
	INSTANCE;

	public void leaveTheBuilding() {
		System.out.println("Who a baby, I'm outta here!");
	}

	public static void main(String[] args) {
		Elvis elvis = Elvis.INSTANCE;
		elvis.leaveTheBuilding();
	}
}