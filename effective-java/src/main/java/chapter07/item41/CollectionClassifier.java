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

	// 오버로딩을 사용할 때는 혼란스럽지 않게 사용할 수 있도록 주의해야 한다.
	public static void main(String[] args) {
		Collection<?>[] collections = {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};

		for (Collection<?> c : collections) {
			System.out.println(classfiy(c));
		}
	}
}