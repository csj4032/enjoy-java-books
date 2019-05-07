package com.genius.aspect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DisplayName("Reflection Test")
public class ReflectionTest {

	@Test
	@DisplayName("Invoke Method")
	public void invokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String name = "Spring";
		assertThat(name.length(), is(6));

		Method method = String.class.getMethod("length");
		assertThat((Integer) method.invoke(name), is(6));

		assertThat(name.charAt(0), is('S'));

		Method charAtMethod = String.class.getMethod("charAt", int.class);
		assertThat(charAtMethod.invoke(name, 0), is('S'));
	}

	@Test
	public void createObject() throws Exception {
		Date now = (Date) Class.forName("java.util.Date").newInstance();
	}
}
