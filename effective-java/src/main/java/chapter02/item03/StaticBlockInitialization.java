package chapter02.item03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticBlockInitialization {

	private static StaticBlockInitialization instance;

	private StaticBlockInitialization() {
	}

	static {
		instance = new StaticBlockInitialization();
	}

	public static StaticBlockInitialization getInstance() {
		return instance;
	}

	public void print() {
		log.info("It's print() method in StaticBlockInitalization instance.");
		log.info("instance hashCode > " + instance.hashCode());
	}
}
