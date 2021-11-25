package com.sleeper.base.annotate;

import java.lang.annotation.*;


/**
 * @author sleeper
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

}

