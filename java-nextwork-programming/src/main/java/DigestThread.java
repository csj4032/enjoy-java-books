public class DigestThread extends Thread implements Digest {

	private String filename;

	public DigestThread(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		getDigestString(filename);
	}

	public static void main(String[] args) {
		for (String filename : args) {
			Thread t = new DigestThread(filename);
			t.start();
		}
	}
}
