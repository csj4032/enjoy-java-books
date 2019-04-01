package chap07;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

	public static void main(String[] args) {
		try {
			URL url = new URL(args[0]);
			URLConnection urlConnection = url.openConnection();
			String line;
			try (InputStream rew = urlConnection.getInputStream()) {
				InputStream buffer = new BufferedInputStream(rew);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(buffer));
				while ((line = bufferedReader.readLine()) != null)
					System.out.println(line);
			}
		} catch (MalformedURLException ex) {
			System.out.println(args[0] + " is not a parseable URL");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}