package chapter04.item19;

public class HelpMethod {

	public String override() {
		return "HelpMethod override";
	}

	public String overrideUsed() {
		return override();
		//return overrideHelper();
	}

	private String overrideHelper() {
		return "HelpMethod";
	}
}