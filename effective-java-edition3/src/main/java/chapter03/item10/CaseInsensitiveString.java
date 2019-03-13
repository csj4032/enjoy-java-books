package chapter03.item10;

import java.util.Objects;
import java.util.regex.Pattern;

public class CaseInsensitiveString {

	private final String s;

	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveString)
			return s.equalsIgnoreCase(
					((CaseInsensitiveString) o).s);
		if (o instanceof String)
			return s.equalsIgnoreCase((String) o);
		return false;
	}

	public static void main(String[] args) {
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		String s = "polish";

		// 대칭성 위반
		cis.equals(s);
		s.equals(cis);

		Pattern pattern;

		PrivateClass privateClass = new PrivateClass();
	}
}