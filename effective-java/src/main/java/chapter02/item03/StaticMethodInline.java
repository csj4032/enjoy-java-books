package chapter02.item03;

public class StaticMethodInline {

	private static final StaticMethodInline INSTANCE = new StaticMethodInline();

	public static StaticMethodInline getInstance() {
		return INSTANCE;
	}

	public StaticMethodInline newInstance() {
		return INSTANCE;
	}

	private StaticMethodInline() {

	}

	public static void main(String[] args) {
		StaticMethodInline staticMethodInline = StaticMethodInline.getInstance();
		staticMethodInline.newInstance();

	}
}
