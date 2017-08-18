import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Digest {

	static String getDigest(String filename) {
		try (FileInputStream in = new FileInputStream(filename)) {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1) ;
			din.close();
			byte[] digest = sha.digest();
			StringBuilder result = new StringBuilder(filename);
			result.append(" ");
			result.append(DatatypeConverter.printHexBinary(digest));
			return result.toString();
		} catch (IOException | NoSuchAlgorithmException ex) {
			System.out.println(ex);
		}
		return "";
	}

	default String getDigestString(String filename) {
		return getDigest(filename);
	}
}
