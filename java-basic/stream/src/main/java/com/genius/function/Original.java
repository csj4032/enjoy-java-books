package com.genius.function;

import lombok.*;

import java.util.function.Function;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Original {
	static Function<String, Function<String, Function<String, Original>>> ORIGINAL_CREATOR = firstName -> lastName -> email -> new Original(firstName, lastName, email);
	private String firstName;
	private String lastName;
	private String email;
}