package com.sleeper.base.annotate;


import java.lang.annotation.*;

/**
 * @author sleeper
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveData {
}
