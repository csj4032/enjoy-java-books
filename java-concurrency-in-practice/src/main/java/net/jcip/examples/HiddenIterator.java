package net.jcip.examples;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HiddenIterator {

	private final Set<Integer> set = new HashSet<Integer>();

	public synchronized void add(Integer i) {
		set.add(i);
	}

	public synchronized void remove(Integer i) {
		set.remove(i);
	}

	public void addTenThings() {
		Random r = new Random();
		for (int i = 0; i < 1000000; i++)
			add(i);
		System.out.println("DEBUG: added ten elements to " + set);
	}
}
