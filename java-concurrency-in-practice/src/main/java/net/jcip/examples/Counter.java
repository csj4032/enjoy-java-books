package net.jcip.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class Counter {

	@GuardedBy("this")
	private long value;

	public synchronized long getValue() {
		return this.value;
	}

	public synchronized long increment() {
		if (value == Long.MAX_VALUE)
			throw new IllegalStateException("count overflow");
		return ++value;
	}
}
