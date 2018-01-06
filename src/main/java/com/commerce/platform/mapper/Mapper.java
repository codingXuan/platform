package com.commerce.platform.mapper;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by java_ztx on 2018/1/3.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Mapper {
    String value() default "";
}
