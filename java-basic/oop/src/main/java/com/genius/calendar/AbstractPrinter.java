package com.genius.calendar;

public abstract class AbstractPrinter implements Printer {

	protected final Calendar calendar;

	public AbstractPrinter(Calendar calendar) {
		this.calendar = calendar;
	}

	protected void printHeader() {
		System.out.printf(" [%s]Year [%s]Month\n", calendar.getYear(), calendar.getMonth());
		System.out.printf("SU MO TU WE TH FR SA\n");
	}

	protected void printBody() {
		int dayOfMonth = calendar.getDayOfMonth();
		for (int i = 1; i <= 35 + 1; i++) {
			long k = i - dayOfMonth;
			if (k <= 0) {
				System.out.printf("   ");
			} else {
				System.out.printf((k < 10) ? " " : "");
				if (calendar.monthLength() >= k) System.out.print(k);
				System.out.printf(" ");
			}
			if (i % 7 == 0) System.out.println();
		}
	}
}
