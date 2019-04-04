package chapter06.item36;

import java.util.Set;

public class Text {

	public enum Style {
		BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
	}

	public void applyStyles(Set<Style> styles) {
		styles.stream().forEach(System.out::print);
	}
}
