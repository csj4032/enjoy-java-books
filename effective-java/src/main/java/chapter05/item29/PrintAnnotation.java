package chapter05.item29;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class PrintAnnotation {

	static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
		Class<?> annotationType = null;
		try {
			annotationType = Class.forName(annotationTypeName);
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
		return element.getAnnotation(annotationType.asSubclass(Annotation.class));
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: java PrintAnnotation <class> <annotation>");
			System.exit(1);
		}
		String className = args[0];
		String annotationTypeName = args[1];
		Class<?> klass = Class.forName(className);
		System.out.println(getAnnotation(klass, annotationTypeName));
	}
}