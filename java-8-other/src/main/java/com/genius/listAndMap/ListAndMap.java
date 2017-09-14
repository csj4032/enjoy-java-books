package com.genius.listAndMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ListAndMap {

	private static final char[] characters = new char[26 * 2];

	public static ArrayList<String> createStringList(int count) {
		ArrayList<String> l = new ArrayList<String>();

		for (int i = 0; i < count; ++i) {
			StringBuilder sb = new StringBuilder();
			int len = 3 + (int) (Math.random() * 14);
			for (int j = 0; j < len; ++j)
				sb.append(characters[(int) (Math.random() * 52)]);
			l.add(sb.toString());
		}

		return l;
	}

	public static HashMap<String, Integer> createStringMap(ArrayList<String> list) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); ++i)
			map.put(list.get(i), i);

		return map;
	}

	public static void testString(int size) {
		ArrayList<String> list = createStringList(size);
		HashMap<String, Integer> map = createStringMap(list);

		long time = System.currentTimeMillis();
		for (int i = 0, count = size * 1000; i < count; ++i) {
			String key = list.get(i % size);
			for (String s : list) {
				if (key.equals(s))
					break;
			}
		}
		System.out.println(" " + size + " " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		Collections.sort(list);
		for (int i = 0, count = size * 1000; i < count; ++i) {
			String key = list.get(i % size);
			Collections.binarySearch(list, key);
		}
		System.out.println(" " + size + " " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		for (int i = 0, count = size * 1000; i < count; ++i) {
			String key = list.get(i % size);
			map.get(key);
		}
		System.out.println(" " + size + " " + (System.currentTimeMillis() - time) + "ms");
	}

	public static ArrayList<Integer> createIntegerList(int size) {
		ArrayList<Integer> l = new ArrayList();
		for (int i = 0; i < size; ++i) {
			l.add((int) (Math.random() * 1000000));
		}

		return l;
	}

	public static HashMap<Integer, Integer> createIntegerMap(ArrayList<Integer> list) {
		HashMap<Integer, Integer> map = new HashMap();
		for (int i = 0; i < list.size(); ++i)
			map.put(list.get(i), i);

		return map;
	}

	public static void testInteger(int size) {
		ArrayList<Integer> list = createIntegerList(size);
		HashMap<Integer, Integer> map = createIntegerMap(list);

		long time = System.currentTimeMillis();
		for (int i = 0, count = size * 1000; i < count; ++i) {
			int key = list.get(i % size);
			for (Integer s : list) {
				if (key == s)
					break;
			}
		}
		System.out.println("미정렬 list " + size + " " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		Collections.sort(list);
		for (int i = 0, count = size * 1000; i < count; ++i) {
			int key = list.get(i % size);
			Collections.binarySearch(list, key);
		}
		System.out.println("정렬 list " + size + " " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		for (int i = 0, count = size * 1000; i < count; ++i) {
			int key = list.get(i % size);
			map.get(key);
		}
		System.out.println("map " + size + " " + (System.currentTimeMillis() - time) + "ms");
	}

	public static void main(String[] args) {
		for (int i = 'a'; i <= 'z'; ++i)
			characters[i - 'a'] = (char) i;
		for (int i = 'A'; i <= 'Z'; ++i)
			characters[26 + i - 'A'] = (char) i;

		System.out.println("String");
		testString(10);
		testString(100);
		testString(1000);

		System.out.println();

		System.out.println("Integer");
		testInteger(10);
		testInteger(100);
		testInteger(1000);
	}
}
