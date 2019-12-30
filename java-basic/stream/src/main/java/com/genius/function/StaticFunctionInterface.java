package com.genius.function;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class StaticFunctionInterface {

	public static Function<Original, Destination> converter() {
		var destination = new Destination();
		return o -> {
			destination.setEmail(o.getEmail());
			destination.setName(o.getFirstName() + o.getLastName());
			return destination;
		};
	}

	public static Function<Original, Destination> mapping() {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return o -> Destination.builder().emailOver(map.containsKey(o.getEmail())).name(o.getFirstName()+o.getLastName()).email(o.getEmail()).build();
	}
}
