package chapter02.item03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadSafeInitialization {

	private static ThreadSafeInitialization instance;

	private ThreadSafeInitialization() {
	}

	public static synchronized ThreadSafeInitialization getInstance() {
		if (instance == null) instance = new ThreadSafeInitialization();
		return instance;
	}

	public void print () {
		log.info("It's print() method in ThreadSafeInitalization instance.");
		log.info("instance hashCode > " + instance.hashCode());
	}
}
