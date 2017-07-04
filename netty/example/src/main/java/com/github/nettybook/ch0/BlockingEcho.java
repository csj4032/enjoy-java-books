package com.github.nettybook.ch0;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class BlockingEcho {

	public static void main(String[] args) {

		final int DEFAULT_PORT = 5555;
		final String IP = "127.0.0.1";

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

			if (serverSocketChannel.isOpen()) {
				serverSocketChannel.configureBlocking(true);
				serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
				serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));

				System.out.println("Waiting for connections ...");

				while (true) {
					try (SocketChannel socketChannel = serverSocketChannel.accept()) {
						System.out.println("Incoming connection from: " + socketChannel.getRemoteAddress());

						// transmitting data
						while (socketChannel.read(buffer) != -1) {
							buffer.flip();
							socketChannel.write(buffer);

							if (buffer.hasRemaining()) {
								buffer.compact();
							} else {
								buffer.clear();
							}
						}
					} catch (IOException ex) {

					}
				}
			} else {
				System.out.println("The server socket channel cannot be opened!");
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}