package io.iti.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author Jing
 * @time 2022-05-05  15:19
 * @return
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
