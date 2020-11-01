package com.genius.function;

import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Original {
	public static Function<String, Function<String, Function<String, Original>>> ORIGINAL_CREATOR = firstName -> lastName -> email -> new Original(firstName, lastName, email);
	private String firstName;
	private String lastName;
	private String email;
}