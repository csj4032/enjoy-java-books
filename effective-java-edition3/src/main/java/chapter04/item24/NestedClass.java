package chapter04.item24;

import java.util.Iterator;

public class NestedClass {

	private static String topClassField;

	public NonStaticMemberClass getNonStaticMemberClass() {
		return new NonStaticMemberClass();
	}

	public static class StaticMemeberClass {
		public final static String STATIC_MEMBER_CLASS = "STATIC MEMBER CLASS";
	}

	class NonStaticMemberClass {
		public String getName() {
			return "NonStatic Member Class";
		}
	}

	public Iterator<String> anonymousClass() {
		return new Iterator<>() {
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public String next() {
				return null;
			}
		};
	}

	public void localClass() {
		class LocalClass {
			private String local;
			LocalClass(String local) {
				this.local = local;
				System.out.println(this.local);
			}
		}

		new LocalClass("local class");
	}
}