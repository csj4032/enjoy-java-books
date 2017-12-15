package chapter06.item32;

import java.util.EnumSet;
import java.util.Set;

public class Text {

	public enum Style {
		BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
	}

	public void applyStyles(Set<Style> styles) {
		styles.stream().forEach(System.out::println);
	}

	public static void main(String[] args) {
		Text text = new Text();
		text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));

		long elements = 0;
		long oldElements = elements;
		elements |= (1L << Style.BOLD.ordinal());
		System.out.println(elements);
		System.out.println(elements != oldElements);

		elements |= (1L << Style.ITALIC.ordinal());
		System.out.println(elements);
		elements |= (1L << Style.UNDERLINE.ordinal());
		System.out.println(elements);
		elements |= (1L << Style.STRIKETHROUGH.ordinal());
		System.out.println(elements);

		elements &= ~(1L << Style.ITALIC.ordinal());
		elements &= ~(1L << Style.UNDERLINE.ordinal());
		System.out.println(elements);
		System.out.println(Long.bitCount(elements));
		System.out.println(Long.toBinaryString(elements));
	}
}