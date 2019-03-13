package chapter03.item13;

import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class PhoneNumber implements Cloneable, Comparator {

	private final short areaCode, prefix, lineNum;
	private static final Comparator<PhoneNumber> COMPARATOR = comparingInt((PhoneNumber pn) -> pn.areaCode).thenComparingInt(pn -> pn.prefix).thenComparingInt(pn -> pn.lineNum);

	public PhoneNumber(int areaCode, int prefix, int lineNum) {
		this.areaCode = rangeCheck(areaCode, 999, "지역코드");
		this.prefix = rangeCheck(prefix, 999, "프리픽스");
		this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
	}

	private static short rangeCheck(int val, int max, String arg) {
		if (val < 0 || val > max)
			throw new IllegalArgumentException(arg + ": " + val);
		return (short) val;
	}

	@Override
	public PhoneNumber clone() {
		try {
			return (PhoneNumber) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	public int compareTo(PhoneNumber pn) {
		//return Comparator.comparingInt((PhoneNumber p) -> p.areaCode).thenComparingInt(p -> p.prefix).thenComparingInt(p -> p.lineNum).compare(this, pn);
		return COMPARATOR.compare(this, pn);
	}

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
