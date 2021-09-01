package com.shenzhijie.loguserinfo.web.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description:TODO(注解-->实现token校验)</p>
 *
 * @date 2021/1/27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheck {
    // 是否校验token
    boolean required() default true;
}
