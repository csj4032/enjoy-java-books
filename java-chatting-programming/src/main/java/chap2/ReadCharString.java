package chap2;

import java.io.*;

public class ReadCharString {
	static FileInputStream fin;
	static DataInputStream dis;

	public static void main(String args[]) {
		char ch;
		String sdata1, sdata2;
		try {
			fin = new FileInputStream("chardata.txt");
			dis = new DataInputStream(fin);
			ch = dis.readChar();
			sdata1 = dis.readUTF();
			sdata2 = dis.readUTF();
			System.out.println(ch);
			System.out.println(sdata1);
			System.out.println(sdata2);
		} catch (EOFException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}