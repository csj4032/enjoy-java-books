package chapter02.item03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LazyInitialization {

	private static LazyInitialization instance;

	private LazyInitialization() {
	}

	public static LazyInitialization getInstance() {
		if (instance == null) instance = new LazyInitialization();
		return instance;
	}

	public void print() {
		log.info("{}", instance.hashCode());
	}
}
