package chap1;

import java.io.IOException;

public class DisplayString {

	public static void main(String[] args) throws IOException {
		byte[] buffer;
		for (int i = 0; i < args.length; i++) {
			buffer = args[1].getBytes();
			System.out.write(buffer);
			System.out.write('\n');
		}
	}
}