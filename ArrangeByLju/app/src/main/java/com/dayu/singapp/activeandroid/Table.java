package com.dayu.singapp.activeandroid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

	public static final String DEFAULT_ID_NAME = "Id";
	public String name();
	public String id() default DEFAULT_ID_NAME;
}
