package chapter02.item03;

public class InitializationOnDemandHolderIdiom {

	private InitializationOnDemandHolderIdiom() {
	}

	private static class Singleton {
		private static final InitializationOnDemandHolderIdiom instance = new InitializationOnDemandHolderIdiom();
	}

	public static InitializationOnDemandHolderIdiom getInstance() {
		System.out.println("create instance");
		return Singleton.instance;
	}
}
