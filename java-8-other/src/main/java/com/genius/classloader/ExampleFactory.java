package com.genius.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ExampleFactory {

	public static IExample newInstance() {
		try {
			return newInstanceWithThrows();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	private static IExample newInstanceWithThrows() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		URLClassLoader tmp = new URLClassLoader(new URL[]{getClassPath()}) {
			public Class<?> loadClass(String name)
					throws ClassNotFoundException {
				if ("com.genius.classloader.Example".equals(name) || "com.genius.classloader.Leak".equals(name))
					return findClass(name);
				return super.loadClass(name);
			}
		};
		return (IExample) tmp.loadClass("com.genius.classloader.Example").newInstance();
	}

	private static URL getClassPath() {
		String dir = "file:/Users/genius/Workspace/enjoy-java8/java-8-other/build/classes/main/";
		try {
			return new URL(dir);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}