package com.genius.calendar;

public abstract class AbstractPrinter implements Printer {

    protected final Calendar calendar;

    public AbstractPrinter(Calendar calendar) {
        this.calendar = calendar;
    }

    protected void printHeader() {
        System.out.printf("[%s]Year [%s]Month\n", calendar.getYear(), calendar.getMonth());
        System.out.printf("SU MO TU WE TH FR SA\n");
    }

    protected void printBody() {
        int dayOfMonth = calendar.getDayOfMonth();
        int endOfMonth = calendar.monthLength() + dayOfMonth;
        for (int i = 0; i < 10; i++) {

        }
        System.out.println();
    }

    private void printDate(int k) {
        System.out.printf("%s%s ", (k < 10) ? " " : "", k <= 0 ? " " : k);
    }

    private boolean isNextLine(int i) {
        return i % 7 == 0;
    }
}