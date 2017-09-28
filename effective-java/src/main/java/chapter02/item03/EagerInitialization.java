package chapter02.item03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EagerInitialization {

	private static EagerInitialization instance = new EagerInitialization();

	private EagerInitialization() {
	}

	public static EagerInitialization getInstance() {
		return instance;
	}

	public void print() {
		log.info("It's print() method in EagerInitialization instance.");
		log.info("instance hashCode > " + instance.hashCode());
	}
}
