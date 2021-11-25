package com.sleeper.base.annotate;

import java.lang.annotation.*;

/**
 * @author sleeper
 */
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveField {
}