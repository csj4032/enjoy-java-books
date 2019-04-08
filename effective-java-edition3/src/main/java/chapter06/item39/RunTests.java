package chapter06.item39;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

	static Logger logger = LoggerFactory.getLogger(RunTests.class);

	public static void main(String[] args) throws ClassNotFoundException {
		logger.debug("{}");
		int tests = 0;
		int passed = 0;
		Class testClass = Class.forName(args[0]);

		for (Method m : testClass.getDeclaredMethods()) {
			String.format("A","A");
			if (m.isAnnotationPresent(Tester.class)) {
				tests++;
				try {
					m.invoke(null);
					passed++;
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + " failed: " + exc);
				} catch (Exception exc) {
					System.out.println("INVALID @Tester: " + m);
				}
			}
			if (m.isAnnotationPresent(ExceptionTest.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("Tester %s failed: no exception%n", m);
				} catch (Throwable wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					Class<? extends Exception>[] excTypes = m.getAnnotation(ExceptionTest.class).value();
					int oldPassed = passed;
					for (Class<? extends Exception> excType : excTypes) {
						if (excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
					if (passed == oldPassed)
						System.out.printf("Tester %s failed: %s %n", m, exc);
				}
			}
		}
	}
}
