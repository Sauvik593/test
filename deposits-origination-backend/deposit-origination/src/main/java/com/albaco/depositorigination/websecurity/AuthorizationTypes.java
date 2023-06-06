package com.albaco.depositorigination.websecurity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizationTypes {
    boolean openToUnAuthenticatedSessionUsers() default false;

    boolean openToAll() default false;

    boolean internalForward() default false;
}
