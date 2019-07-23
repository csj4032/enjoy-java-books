package com.genius.calendar;

public class PrinterEnglish extends AbstractPrinter {

	public PrinterEnglish(Calendar calendar) {
		super(calendar);
	}

	@Override
	public void print() {
		printHeader();
		printBody();
	}
}