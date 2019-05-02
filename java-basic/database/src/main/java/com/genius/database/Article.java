package com.genius.database;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	private LocalDateTime regDate;
}
