package chapter04.item19;

public class HelpMethodSub extends HelpMethod {

	@Override
	public String override() {
		return "HelpMethodSub override";
	}

	public static void main(String[] args) {
		HelpMethod helpMethod = new HelpMethod();
		System.out.println(helpMethod.overrideUsed());
		HelpMethodSub helpMethodSub = new HelpMethodSub();
		System.out.println(helpMethodSub.overrideUsed());
	}
}