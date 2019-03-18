package chapter04.item15;

public class Foo {

	private PackagePrivate packagePrivate;

	public String getName() {
		packagePrivate = new PackagePrivate("a");
		String type = packagePrivate.type;
		return "name is foo";
	}
}
