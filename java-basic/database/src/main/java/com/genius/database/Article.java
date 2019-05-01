package com.genius.database;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private long id;
	private int grp;
	private int ordinal;
	private int level;
	private String subject;
	private int authorId;
	private int status;
}
