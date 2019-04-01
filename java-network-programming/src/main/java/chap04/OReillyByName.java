package chap04;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {

	private static final String HOST = "www.oreilly.com";

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName(HOST);
			System.out.println(address);
		} catch (UnknownHostException ex) {
			System.out.println("Could not find " + HOST);
		}
	}
}
