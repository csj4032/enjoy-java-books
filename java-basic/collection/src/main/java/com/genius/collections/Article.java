package com.genius.collections;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Comparable<Article> {
	private Integer id;
	private String subject;
	private String contents;

	@Override
	public int compareTo(Article o) {
		return this.id.compareTo(o.id);
	}
}
