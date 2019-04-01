package async;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AsynchronousCompletionHandler<T> implements CompletionHandler<T, CompletableFuture<T>> {

	private static final ConcurrentHashMap<Class<?>, AsynchronousCompletionHandler<?>> cache = new ConcurrentHashMap<>();

	static <T> AsynchronousCompletionHandler<T> getInstance(Class<T> clazz) {
		@SuppressWarnings("unchecked")
		AsynchronousCompletionHandler<T> handler = (AsynchronousCompletionHandler<T>) cache.computeIfAbsent(clazz, c -> new AsynchronousCompletionHandler<T>());
		return handler;
	}

	public static CompletableFuture<Integer> readAsync(AsynchronousFileChannel channel, ByteBuffer dst, long position) {
		CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
		channel.read(dst, position, completableFuture, getInstance(Integer.class));
		return completableFuture;
	}

	@Override
	public void completed(T result, CompletableFuture<T> attachment) {
		attachment.complete(result);
	}

	@Override
	public void failed(Throwable exc, CompletableFuture<T> attachment) {
		attachment.completeExceptionally(exc);
	}
}
