package chapter04.item18;

import java.util.Properties;

public class PropertiesClass {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("a", "a");
		properties.getProperty("a");
		properties.get("a");
	}
}
