package com.genius.asyncFile;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.nio.file.StandardOpenOption.READ;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsyncFileTest {

	@Test
	public void givenPath_whenReadsContentWithFuture_thenCorrect() throws IOException, ExecutionException, InterruptedException {
		Path path = Paths.get(URI.create(this.getClass().getClassLoader().getResource("asyncFile/file.txt").toString()));
		AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, READ);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Future<Integer> operation = asynchronousFileChannel.read(buffer, 0);
		operation.get();
		String fileContent = new String(buffer.array()).trim();
		buffer.clear();
		assertEquals(fileContent, "genius.com");
	}

	@Test
	public void givenPathAndContent_whenWritesToFileWithFuture_thenCorrect() throws IOException, ExecutionException, InterruptedException {
		Path path = Paths.get(URI.create(this.getClass().getClassLoader().getResource("asyncFile/temp.txt").toString()));
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		long position = 0;
		buffer.put("hello world".getBytes());
		buffer.flip();
		Future<Integer> operation = fileChannel.write(buffer, position);
		buffer.clear();
		operation.get();
		String content = readContent(path);
		assertEquals("hello world", content);
	}

	public static String readContent(Path file) throws ExecutionException, InterruptedException {
		AsynchronousFileChannel fileChannel = null;
		try {
			fileChannel = AsynchronousFileChannel.open(file, StandardOpenOption.READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Future<Integer> operation = fileChannel.read(buffer, 0);
		operation.get();
		String fileContent = new String(buffer.array()).trim();
		buffer.clear();
		return fileContent;
	}
}
