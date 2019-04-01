package chap03;

public class ReturnDigest extends Thread implements Digest {

	private String filename;
	private byte[] digest;

	public ReturnDigest(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		digest = getDigestByte(filename);
	}

	public byte[] getDigest() {
		return digest;
	}
}
