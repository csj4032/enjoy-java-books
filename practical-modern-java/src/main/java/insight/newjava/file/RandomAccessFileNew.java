package insight.newjava.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RandomAccessFileNew {


	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("User :");
			return;
		}


		Path file = Paths.get(args[0]);
		System.out.format("%s%n", file);
		System.out.format("%s%n", file);

		String s = "Java New Features\n";
		byte data[] = s.getBytes();

		ByteBuffer out = ByteBuffer.wrap(data);
		ByteBuffer copy = ByteBuffer.allocate(1024);

		try (FileChannel fileChannel = (FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE))) {
			int index;
			do {
				index = fileChannel.read(copy);
			} while (index != -1 && copy.hasRemaining());

			fileChannel.position(0);

			while (out.hasRemaining()) {
				fileChannel.write(out);
			}
			out.rewind();

			long length = fileChannel.size();
			fileChannel.position(length - 1);
			copy.flip();

			while (copy.hasRemaining()) {
				fileChannel.write(copy);
			}

			while (out.hasRemaining()) {
				fileChannel.write(out);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
