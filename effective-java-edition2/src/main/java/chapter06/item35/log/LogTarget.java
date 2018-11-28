package chapter06.item35.log;

public class LogTarget {

	@LogAnnotation(message = "등록", logType = LogType.SUBJECT, targets = {
			@LogTargetAnnotation(order = "A", target = "userId", joiner = " / "),
			@LogTargetAnnotation(order = "B", target = "deptName", joiner = " / "),
			@LogTargetAnnotation(order = "C", target = "userName")
	})
	public void logTargetMethod() {
		System.out.println("logTargetMethod");
	}

	@LogAnnotation(message = "수정", logType = LogType.SUBJECT)
	public void logTargetMethod2() {
		System.out.println("logTargetMethod2");
	}
}