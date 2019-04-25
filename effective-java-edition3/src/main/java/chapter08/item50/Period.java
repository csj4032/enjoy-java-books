package chapter08.item50;

import java.time.LocalDate;

public class Period {

    private LocalDate start;
    private LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        if (!start.isBefore(end)) throw new IllegalArgumentException(start + " after " + end);
        //this.start = LocalDate.from(start);
        //this.end = LocalDate.from(end);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
