package com.genius.calendar;

public class Printer {

	private final Calendar calendar;
	private final static int MonthStart = 1;
	private final static int Decimal = 10;
	private final static int DaysOfWeek = 7;
	private final static int MonthEnd = 35;

	public Printer(Calendar calendar) {
		this.calendar = calendar;
	}

	public void print() {
		printHeader();
		printBody();
	}

	private void printBody() {
		for (int i = MonthStart; i <= MonthEnd; i++) {
			long k = i - calendar.getDayOfMonth();
			if (k <= 0) {
				printSpace("   ");
			} else {
				printSpace((k < Decimal) ? " " : "");
				if (k <= calendar.getCurrentMonthLength()) System.out.print(k);
				printSpace(" ");
			}
			if (isNewLine(i)) System.out.println();
		}
	}

	private void printSpace(String whiteSpace) {
		System.out.print(whiteSpace);
	}

	private boolean isNewLine(int i) {
		return i % DaysOfWeek == 0;
	}

	private void printHeader() {
		System.out.printf("   [%s]년 [%s]월\n", calendar.getYear(), calendar.getMonth());
		System.out.printf("SU MO TU WE TH FR SA\n");
	}
}