package chapter04.oop;

public class Copy {

    private static final int EOF = -1;

    public void copy(Reader reader, Writer writer) {
        int c;
        while ((c = reader.getChar()) != EOF) {
            writer.putChar(c);
        }
    }

    public static void main(String[] args) {
        Copy copy = new Copy();
        copy.copy(new Keyboard(new char[]{'a','b'}), new Printer());
    }
}