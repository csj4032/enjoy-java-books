package chapter04.oop;

public class Keyboard implements Reader {

    private final char chars[];
    private int index = -1;

    public Keyboard(char[] chars) {
        this.chars = chars;
    }

    @Override
    public int getChar() {
        index++;
        if(index >= chars.length) return -1;
        return chars[index];
    }
}
