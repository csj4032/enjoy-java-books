package chapter03.item10;

import java.util.HashMap;
import java.util.Map;

public class EqualsSub extends EqualsSuper {

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EqualsSub) {
			EqualsSub o = (EqualsSub) obj;
			if (this.getAge() == o.getAge()) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		EqualsSub equalsSub1 = new EqualsSub();
		equalsSub1.setName("홍길동");
		equalsSub1.setAge("홍길동");

		EqualsSub equalsSub2 = new EqualsSub();
		equalsSub2.setName("홍길동");
		equalsSub2.setAge("홍길동");

		var result = equalsSub1.equals(equalsSub2);
		System.out.println(result);

		Map<EqualsSub, String> map = new HashMap<>();
		map.put(equalsSub1, "equalsSub1");
		map.put(equalsSub2, "equalsSub2");

		var contain = map.containsKey(equalsSub2);
		System.out.println(contain);
	}
}