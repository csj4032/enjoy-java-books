package com.genius.database;

import org.junit.jupiter.api.*;

import java.sql.SQLDataException;
import java.sql.SQLException;

@DisplayName("ArticleDao")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleDaoTest {

	private static ArticleDao articleDao;
	private static Article article1;
	private static Article article2;
	private static Article article3;

	@BeforeAll
	public static void setUp() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setConnectionManager(new HomeConnectionManager());
		articleDao = new DaoFactory().articleDao();
		articleDao.setJdbcTemplate(jdbcTemplate);
		articleDao.truncate();
		article1 = Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build();
		article2 = Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build();
		article3 = Article.builder().grp(1).ordinal(1).level(1).subject("제목").authorId(1).status(1).build();
	}

	@Test
	@Order(1)
	@DisplayName("아티클 저장")
	public void add() {
		int result = articleDao.add(article1) + articleDao.add(article2) + articleDao.add(article3);
		Assertions.assertEquals(3, result);
	}

	@Test
	@Order(2)
	@DisplayName("특정 아이디 아티클 조회")
	public void get() {
		Article article = articleDao.get(1);
		Assertions.assertEquals(1, article.getId());
		Assertions.assertEquals("제목", article.getSubject());
	}

	@Test
	@Order(3)
	@DisplayName("전체 아티클 삭제")
	public void deleteAll() {
		articleDao.deleteAll();
		Assertions.assertEquals(0, articleDao.getCount());
	}

	@Test
	@Order(4)
	@DisplayName("특정 아이디 아티클 조회")
	public void getFailure() {
		Assertions.assertThrows(SQLDataException.class, () -> articleDao.get(1));
	}
}
