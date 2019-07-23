package com.genius.calendar;

public class PrinterKorean extends AbstractPrinter {

	public PrinterKorean(Calendar calendar) {
		super(calendar);
	}

	@Override
	public void print() {
		printHeader();
		printBody();
	}

	@Override
	protected void printHeader() {
		System.out.printf("   [%s]년 [%s]월\n", calendar.getYear(), calendar.getMonth());
		System.out.printf("일  월  화 수 목  금  토\n");
	}
}
