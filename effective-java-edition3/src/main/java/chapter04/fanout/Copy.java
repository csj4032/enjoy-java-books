package chapter04.fanout;

public class Copy {

    // High Level depends on Low Level
    // Fan-out
    private boolean GptFlag = false;
    private boolean GpunchFlag = false;

    private KeyBoard keyBoard;
    private Punch punch;

    private Printer printer;
    private PunchCard punchCard;

    private static final int EOF = -1;

    public void copy() {
        int c;
        while ((c = (GptFlag ? readKb() : readPc())) != EOF) {
            if (GpunchFlag) {
                writePunch(c);
            } else {
                writePrinter(c);
            }
        }
    }

    private void writePrinter(int c) {
        printer.write(c);
    }

    private void writePunch(int c) {
        punchCard.write(c);
    }

    private int readKb() {
        return keyBoard.read();
    }

    private int readPc() {
        return punch.read();
    }
}
