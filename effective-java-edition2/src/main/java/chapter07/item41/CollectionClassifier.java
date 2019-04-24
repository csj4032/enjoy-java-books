package chapter07.item41;

import java.util.*;

public class CollectionClassifier {

	public static String classfiy(Set<?> s) {
		return "Set";
	}

	public static String classfiy(List<?> lst) {
		return "List";
	}

	public static String classfiy(Collection<?> c) {
		return "Collection";
	}

	public static void main(String[] args) {
		Collection<?>[] collections = {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};

		for (Collection<?> c : collections) {
			System.out.println(classfiy(c));
		}
	}
}