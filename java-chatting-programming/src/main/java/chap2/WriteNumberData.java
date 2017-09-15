package chap2;

import java.io.*;

public class WriteNumberData {

	static FileOutputStream fout;
	static DataOutputStream dos;

	public static void main(String args[]) {
		try {
			fout = new FileOutputStream("numberdata.txt");
			dos = new DataOutputStream(fout);
			dos.writeBoolean(true);
			dos.writeDouble(989.27);
			for (int i = 1; i <= 500; i++) {
				dos.writeInt(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}