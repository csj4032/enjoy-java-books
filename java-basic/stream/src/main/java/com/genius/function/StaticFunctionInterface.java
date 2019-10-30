package com.genius.function;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StaticFunctionInterface {

	private Function<Original, Destination> mapping =  o -> Destination.builder().name(o.getFirstName()+o.getLastName()).email(o.getEmail()).build();

	public static Function<Original, Destination> converter() {
		System.out.println("converter 호출");
		var destination = new Destination();
		return o -> {
			System.out.println("converter가 반환한 function을 map 에서 호출");
			destination.setEmail(o.getEmail());
			destination.setName(o.getFirstName() + o.getLastName());
			return destination;
		};
	}

	public static Function<Original, Destination> mapping() {
		// originals 리스트에 대한 비즈니스 로직을 추가로 넣을 수 있음
		// 이메일 중복을 확인 해준다.
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return o -> Destination.builder().emailOver(map.containsKey(o.getEmail())).name(o.getFirstName()+o.getLastName()).email(o.getEmail()).build();
	}

	public void converting() {
		var originals = List.of(
				Original.builder().email("!").firstName("@@").lastName("##").build(),
				Original.builder().email("1").firstName("22").lastName("33").build(),
				Original.builder().email("2").firstName("22").lastName("33").build(),
				Original.builder().email("1").firstName("22").lastName("33").build()
		);
		var destinations1 = originals.stream().map(converter()).collect(Collectors.toList());
		var destinations2 = originals.stream().map(mapping()).collect(Collectors.toList());
		var destinations3 = originals.stream().map(mapping).collect(Collectors.toList());
		System.out.println(destinations1);
		System.out.println(destinations2);
		System.out.println(destinations3);
	}

	public static void main(String[] args) {
		var staticFunctionInterface = new StaticFunctionInterface();
		staticFunctionInterface.converting();
	}
}
