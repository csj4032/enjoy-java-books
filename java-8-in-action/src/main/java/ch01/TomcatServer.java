package ch01;

import org.apache.catalina.startup.Bootstrap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TomcatServer {

	public static void main(String[] args) throws Exception {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.start();

		Stream.of("Google", "Naver", "Daum", "google", "Yahoo", "Bing")
				.filter(s -> s.length() > 4)
				.map(s -> s.toUpperCase())
				.distinct()
				.sorted()
				.collect(toList());

		int[] ints = new int[]{1, 2, 3};
		List list1 = Arrays.asList(ints);
		List list2 = Arrays.asList(1, 2, 3);
		System.out.println(Arrays.toString((int[]) list1.get(0)));
		System.out.println(list2);
	}
}