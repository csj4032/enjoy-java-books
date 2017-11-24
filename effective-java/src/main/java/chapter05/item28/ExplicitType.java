package chapter05.item28;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.HashMap;

public class ExplicitType {

	public void explicit(HashMap<String, Number> param) {
		param = Maps.newHashMap(ImmutableMap.<String, Number>builder().put("A", 1).put("B", 2).build());
	}
}