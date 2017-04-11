package ch03;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class ExecuteAround {

	public static void main(String[] args) throws IOException {
		String oneLine = processFileLimited();
		log.info("{}", oneLine);

		String twoLine = processFile((b) -> b.readLine() + b.readLine());
		log.info("{}", twoLine);
	}

	public static String processFileLimited() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("/ch03/data.txt"))) {
			return br.readLine();
		}
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("/ch03/data.txt"))) {
			return p.process(br);
		}
	}

	public interface BufferedReaderProcessor {
		String process(BufferedReader b) throws IOException;
	}
}