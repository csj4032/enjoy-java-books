package chap03;

import java.util.function.BiConsumer;

public class CallbackDigest implements Digest, Runnable {

	private String filename;
	private BiConsumer<String, byte[]> consumer;

	public CallbackDigest(String filename, BiConsumer<String, byte[]> consumer) {
		this.filename = filename;
		this.consumer = consumer;
	}

	@Override
	public void run() {
		getDigestCallback(filename, consumer);
	}
}
