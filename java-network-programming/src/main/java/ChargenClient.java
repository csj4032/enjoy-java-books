import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {

	public static int DEFAULT_PORT = 55286;
	public static String DEFAULT_HOSTNAME = "localhost";

	public static void main(String[] args) {
		try {
			SocketAddress address = new InetSocketAddress(DEFAULT_HOSTNAME, DEFAULT_PORT);
			SocketChannel client = SocketChannel.open(address);

			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);

			while (client.read(buffer) != -1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}