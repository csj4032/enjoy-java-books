package ch03;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class ExecuteAround {

	public static void main(String[] args) throws IOException {

		ClassLoader classLoader = new ExecuteAround().getClass().getClassLoader();
		File file = new File(classLoader.getResource("ch03/data.txt").getFile());

		String oneLine = processFileLimited(file);
		log.info("{}", oneLine);

		String twoLine = processFile(b -> b.readLine() + b.readLine(), file);
		log.info("{}", twoLine);
	}

	public static String processFileLimited(File file) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			return br.readLine();
		}
	}

	public static String processFile(BufferedReaderProcessor p, File file) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			return p.process(br);
		}
	}

	public interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}
}