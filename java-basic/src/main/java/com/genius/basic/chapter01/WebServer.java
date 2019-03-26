package com.genius.basic.chapter01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class WebServer {

	private static final int PORT = 9000;
	private static final String HTTP_RESPONSE = "HTTP/1.1 200 OK\r\n\r\n";

	public static void main(String[] args) throws IOException {

		Integer.valueOf("1");
		ServerSocket serverConnect = new ServerSocket(PORT);
		while (true) {
			try (
					Socket socket = serverConnect.accept();
					InputStream inputStream = socket.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					OutputStream outputStream = socket.getOutputStream();
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
					BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)
			) {
				var line = bufferedReader.readLine();
				while (!line.isEmpty()) {
					System.out.println(line);
					line = bufferedReader.readLine();
				}
				bufferedWriter.write(HTTP_RESPONSE + new Date());
				bufferedWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
