package com.niit.tests;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DBConfigurationTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(" com.niit");
		context.refresh();

	}

}
