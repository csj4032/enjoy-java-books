package chap03;

import javax.xml.bind.DatatypeConverter;
import java.util.function.BiConsumer;

public class CallbackDigestUserInterface {

	public static void main(String[] args) {
		for (String filename : args) {
			CallbackDigest cb = new CallbackDigest(filename, getStringBiConsumer());
			new Thread(cb).run();
		}
	}

	public static BiConsumer<String, byte[]> getStringBiConsumer() {
		return (f, b) -> {
			StringBuilder result = new StringBuilder(f);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(b));
			System.out.println(result);
		};
	}
}