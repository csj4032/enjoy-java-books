package async;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

import static async.AsynchronousCompletionHandler.readAsync;

public class FindReadAndFuture {

	public static void main(String[] args) throws URISyntaxException {
		ByteBuffer buffer = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(100);
		String encoding = System.getProperty("file.encoding");

		Path path = Paths.get(FindReadAndFuture.class.getResource("/async/story.txt").toURI());
		Path path2 = Paths.get(FindReadAndFuture.class.getResource("/async/story2.txt").toURI());
		try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			 AsynchronousFileChannel asynchronousFileChannel2 = AsynchronousFileChannel.open(path2, StandardOpenOption.READ)) {
			CompletableFuture<Integer> result = readAsync(asynchronousFileChannel, buffer, 0);
			CompletableFuture<Integer> result2 = readAsync(asynchronousFileChannel2, buffer2, 0);

			while (!result.isDone() && !result2.isDone()) {
				System.out.println("Do something..");
			}
			System.out.println("Read done: " + result.isDone());
			System.out.println("Read done: " + result2.isDone());
			System.out.println("Bytes read: " + result.get());
			System.out.println("Bytes read: " + result2.get());


		} catch (Exception ex) {
			System.err.println(ex);
		}

		buffer.flip();
		System.out.print(Charset.forName(encoding).decode(buffer));
		buffer.clear();
	}
}
