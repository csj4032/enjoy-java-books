package chapter03.item10;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumber {

	private final short areaCode;
	private final short prefix;
	private final short lineNumber;

	public PhoneNumber(int areaCode, int prefix, int lineNumber) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "line Number");
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
	}

	private static void rangeCheck(int arg, int max, String name) {
		if (arg < 0 || arg > max) throw new IllegalArgumentException(name + ":" + arg);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof PhoneNumber)) return false;
		PhoneNumber pn = (PhoneNumber) o;
		return pn.lineNumber == lineNumber && pn.prefix == prefix && pn.areaCode == areaCode;
	}

	// Broken - no hashCode method!

	// A decent hashCode method - Page 48
	// @Override public int hashCode() {
	// int result = 17;
	// result = 31 * result + areaCode;
	// result = 31 * result + prefix;
	// result = 31 * result + lineNumber;
	// return result;
	// }

	// Lazily initialized, cached hashCode - Page 49
	private volatile int hashCode; // (See Item 71)

	@Override
	public int hashCode() {
		int result = hashCode;
		if (result == 0) {
			result = 17;
			result = 31 * result + areaCode;
			result = 31 * result + prefix;
			result = 31 * result + lineNumber;
			hashCode = result;
		}
		return result;
	}

	@Override
	public String toString() {
		return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
	}

	public static void main(String[] args) {
		Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
		m.put(new PhoneNumber(707, 867, 5309), "Jenny");
		System.out.println(m);
	}
}