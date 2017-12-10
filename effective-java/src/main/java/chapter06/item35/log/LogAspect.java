package chapter06.item35.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("@annotation(logAnnotation)")
	public void callAt(LogAnnotation logAnnotation) {
	}

	@Around("callAt(logAnnotation)")
	public Object around(ProceedingJoinPoint pjp, LogAnnotation logAnnotation) throws Throwable {
		logger.info(logAnnotation.toString());
		return logAnnotation.logType().equals(LogType.NORMAL) ? null : pjp.proceed();
	}
}