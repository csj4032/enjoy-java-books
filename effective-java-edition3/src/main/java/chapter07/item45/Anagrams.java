package chapter07.item45;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Anagrams {

    private static String alphabetize(String word) {
        char[] a = word.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    public Map<String, Set<String>> groups(String dictionary) throws URISyntaxException {
        Map<String, Set<String>> groups = new HashMap<>();
        URI path = Anagrams.class.getResource(dictionary).toURI();
        File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String word = sc.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Map<String, List<String>> groupsStream(String dictionary) throws URISyntaxException, IOException {
        try (Stream<String> words = Files.lines(Path.of(Anagrams.class.getResource(dictionary).toURI()))) {
            return words.collect(groupingBy(word ->
                    word.chars().sorted().collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append).toString()));
        }
    }

    public Map<String, List<String>> groupsStreamWithAlphabetize(String dictionary) throws URISyntaxException, IOException {
        try (Stream<String> words = Files.lines(Path.of(Anagrams.class.getResource(dictionary).toURI()))) {
            return words.collect(groupingBy(word -> alphabetize(word)));
        }
    }
}
