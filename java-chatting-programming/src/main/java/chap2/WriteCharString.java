package chap2;

import java.io.*;

public class WriteCharString {
	static DataOutputStream dos;

	public static void main(String args[]) {
		try {
			String data;
			FileOutputStream fout = new FileOutputStream("chardata.txt");
			dos = new DataOutputStream(fout);
			dos.writeChar(65);
			dos.writeUTF("A");
			dos.writeUTF("B");
		} catch (EOFException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (dos != null) dos.close();
				if (dos != null) dos.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}