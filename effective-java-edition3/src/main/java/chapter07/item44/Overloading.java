package chapter07.item44;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Overloading<T> {

    private T data;

    public void overloading(Runnable runnable) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(runnable);
    }

    public void overloading(Callable<String> callable) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(callable);

        new Thread(System.out::println).start();

        ExecutorService exec = Executors.newCachedThreadPool();
        //exec.submit(System.out::println);
        exec.submit((Runnable) System.out::println);
        //exec.submit(System.out.println("AA"));
        exec.submit(() -> System.out.println("AA"));
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String resourcePath = "클라이언트에서_보낸_메세지";
        InputStream inputStream = new ByteArrayInputStream(resourcePath.getBytes("UTF-8"));
        char[] buff = new char[1];
        int len;
        try (InputStreamReader br = new InputStreamReader(inputStream, "UTF-8")) {
            while ((len = br.read(buff)) != -1) {
                System.out.println(new String(buff, 0, len) + " " + Integer.toHexString(buff[0]) + " " + Integer.toBinaryString(buff[0]));
            }
        } catch (IOException ex) {
            System.out.println("익셉션 발생: " + ex.getMessage());
        }
    }
}
