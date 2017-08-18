package ch03;

public class DigestRunnable implements Digest, Runnable {

	private String filename;

	public DigestRunnable(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		System.out.println(getDigestString(filename));
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new DigestRunnable(args[0]));
		thread.start();
	}
}
