package com.genius.collection;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Writer {
	private int id;
	private String name;
}
