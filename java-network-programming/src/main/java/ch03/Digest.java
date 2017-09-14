package ch03;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.BiConsumer;

public interface Digest {

	static byte[] getDigest(String filename) {
		byte[] digest = null;
		try (FileInputStream in = new FileInputStream(filename)) {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1) ;
			din.close();
			digest = sha.digest();
		} catch (IOException | NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return digest;
	}

	default String getDigestString(String filename) {
		StringBuilder builder = new StringBuilder(filename);
		builder.append(": ");
		builder.append(DatatypeConverter.printHexBinary(getDigest(filename)));
		return builder.toString();
	}

	default byte[] getDigestByte(String filename) {
		byte[] d = getDigest(filename);
		System.out.println(d);
		return d;
	}

	default void getDigestCallback(String filename, BiConsumer<String, byte[]> consumer) {
		consumer.accept(filename, getDigest(filename));
	}
}
