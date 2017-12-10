package chapter06.item35.log;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface LogAnnotation {
	LogType logType() default LogType.NORMAL;
	LogTargetAnnotation[] targets() default {};
	LogParamAnnotation[] params() default {};
	String message() default "";
}