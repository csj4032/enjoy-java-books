package com.genius.condition;

import com.genius.condition.express.Express;
import com.genius.condition.express.ExpressType;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SafetyNumberTest {

	@Test
	@Order(1)
	@DisplayName("Parameter Null Valid")
	public void getSafetyNumberNullValid() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Assertions.assertThrows(NullPointerException.class, () -> safetyNumber.getExpress(true, null, null, null, null));
	}

	@Test
	@Order(2)
	@DisplayName("true, NEW, REQUEST, AFTER 경우 신청안함")
	public void getSafetyNumber() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(true, IssueType.NEW, IssueStatus.REQUEST, ExpiredType.AFTER, "000-0000-0000");
		Assertions.assertEquals(ExpressType.NO_REQUEST, express.express());
	}

	@Test
	@Order(3)
	@DisplayName("false, NEW, COMPLETE, BEFORE 경우 신청완료_번호")
	public void getSafetyNumberFalseNewCompleteBefore() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.NEW, IssueStatus.COMPETE, ExpiredType.BEFORE, "000-0000-0000");
		Assertions.assertEquals(ExpressType.COMPLETE_NUMBER, express.express());
		Assertions.assertEquals("000-0000-0000", express.express().getMessage());
	}

	@Test
	@Order(4)
	@DisplayName("false, UPDATE, COMPLETE, BEFORE 경우 신청완료_다른_번호")
	public void getSafetyNumberFalseUpdateCompleteBeforeOtherNumber() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.UPDATE, IssueStatus.COMPETE, ExpiredType.BEFORE, "000-0000-1111");
		Assertions.assertEquals(ExpressType.COMPLETE_NUMBER, express.express());
		Assertions.assertEquals("000-0000-1111", express.express().getMessage());
	}

	@Test
	@Order(5)
	@DisplayName("false, WAITING, COMPLETE, BEFORE 경우 신청완료_다른_번호")
	public void getSafetyNumberFalseWaitingCompleteBeforeOtherNumber() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.WAITING, IssueStatus.COMPETE, ExpiredType.BEFORE, "000-0000-1111");
		Assertions.assertEquals(ExpressType.ON_REQUEST, express.express());
		Assertions.assertEquals("발급요청중", express.express().getMessage());
	}

	@Test
	@Order(6)
	@DisplayName("false, NEW, COMPLETE, AFTER 경우")
	public void getSafetyNumberFalseNewCompleteAfter_Hyphen() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.NEW, IssueStatus.COMPETE, ExpiredType.AFTER, "000-0000-1111");
		Assertions.assertEquals(ExpressType.HYPHEN, express.express());
		Assertions.assertEquals("-", express.express().getMessage());
	}

	@Test
	@Order(7)
	@DisplayName("false, WAITING, COMPLETE, AFTER 경우 발급요청중")
	public void getSafetyNumberFalseWaitingCompleteAfter_OnRequest() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.WAITING, IssueStatus.COMPETE, ExpiredType.AFTER, "000-0000-1111");
		Assertions.assertEquals(ExpressType.ON_REQUEST, express.express());
		Assertions.assertEquals("발급요청중", express.express().getMessage());
	}

	@Test
	@Order(8)
	@DisplayName("false, WAITING, FAILURE, AFTER 경우 발급요청중")
	public void getSafetyNumberFalseWaitingFailureAfter_OnRequest() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.WAITING, IssueStatus.FAILURE, ExpiredType.AFTER, "000-0000-1111");
		Assertions.assertEquals(ExpressType.ON_REQUEST, express.express());
		Assertions.assertEquals("발급요청중", express.express().getMessage());
	}

	@Test
	@Order(9)
	@DisplayName("false, WAITING, FAILURE, AFTER 경우 발급실패")
	public void getSafetyNumberFalseNewFailureAfter_FAILURE() {
		SafetyNumber safetyNumber = new SafetyNumber();
		Express express = safetyNumber.getExpress(false, IssueType.NEW, IssueStatus.FAILURE, ExpiredType.AFTER, "000-0000-1111");
		Assertions.assertEquals(ExpressType.FAILURE, express.express());
		Assertions.assertEquals("발급실패", express.express().getMessage());
	}
}