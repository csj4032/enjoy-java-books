package com.genius;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class BeanExample {

	@Test
	public void beanTest() {
		SomeObject mount = new SomeObject();
		ListeningObject listeningObject = new ListeningObject();
		listeningObject.handInListenedToObject(mount);
		mount.setFoobar("chocolate");
		mount.setBarfoo("pizza");
	}
}