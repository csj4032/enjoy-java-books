package chapter03.item10;

import java.net.URL;

//https://brian.pontarelli.com/2006/12/05/mr-gosling-why-did-you-make-url-equals-suck/
public class IPAddressExample {
	public static void main(String args[]) throws Exception {
		URL url = new URL("http://local-alimy.choibom.com");
		URL url1 = new URL("http://localhost");
		System.out.println(url.equals(url1));

		String a = new String("A");
		String b = new String("A");

		String c = "A";
		String d = "A";
	}
}