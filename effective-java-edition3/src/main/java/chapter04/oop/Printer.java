package chapter04.oop;

public class Printer implements Writer {

    @Override
    public void putChar(int c) {
        System.out.println(c);
    }
}