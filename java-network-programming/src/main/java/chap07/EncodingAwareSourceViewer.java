package chap07;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			try {
				String encoding = "UTF-8";
				URL url = new URL(args[i]);
				URLConnection urlConnection = url.openConnection();
				String contentType = urlConnection.getContentType();
				InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
				Reader reader = new InputStreamReader(inputStream, encoding);
				int c;
				while ((c = reader.read()) != -1) {
					System.out.print((char) c);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
