package com.niit.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.UserDAO;
import com.niit.models.User;

public class userTest {
	
	private static AnnotationConfigApplicationContext context=null;
	private static UserDAO userDAO=null;
	private User u=null;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
		
		
	}
	
	@Test
	public void registrationTest() {
		u=new User();
		u.setEmail("anika@gmail.com");
		u.setFirstname("Anika");
		u.setLastname("Sharma");
		u.setPassword("anika");
		u.setPhonenumber("907765543");
		u.setRole("student");
		System.out.println("user details saved");
		assertTrue("Could not save user details", userDAO.registration(u));
	}
	
	

	
}
