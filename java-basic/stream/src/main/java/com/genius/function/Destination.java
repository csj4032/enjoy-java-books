package com.genius.function;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Destination {
	private String name;
	private String email;
	private Boolean emailOver;
}
