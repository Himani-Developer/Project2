package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.UserDAO;
import com.niit.models.ErrorClazz;
import com.niit.models.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		try {
			// IF EMAIL IS NOT UNIQUE
			if (!userDAO.isEmailUnique(user.getEmail())) {
				ErrorClazz errorClazz = new ErrorClazz(2, "Email is already exists..Please choose different email");
				return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				userDAO.registration(user);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		} catch (Exception e) {
			ErrorClazz errorClazz = new ErrorClazz(1, "Something went wrong" + e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody User user,HttpSession session){
		
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		try {
		userDAO.updateUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e) {
			ErrorClazz errorClazz=new ErrorClazz(5, "Unable to update details..try again");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	
	@RequestMapping(value = "/login", method = RequestMethod.PUT)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User validUser = userDAO.login(user);
		if (validUser == null) {
			ErrorClazz errorClazz = new ErrorClazz(3, "invalid Email");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.UNAUTHORIZED);
		} else {

			validUser.setOnline(true); // updating online to true
			userDAO.updateUser(validUser);
			session.setAttribute("loggedInUser", validUser.getEmail());
			System.out.println("session id::" + session.getId());
			System.out.println("Session Attribute ::" + session.getAttribute("loggedInUser"));
			System.out.println("Session created on::" + session.getCreationTime());
			return new ResponseEntity<User>(validUser, HttpStatus.OK);

		}

	}
	
	

	/*@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		
		System.out.println("session id::" + session.getId());
		System.out.println("Session Attribute ::" + session.getAttribute("loggedInUser"));
		System.out.println("Session created on::" + session.getCreationTime());
		String email=(String) session.getAttribute("loggedInUser");
		if(email==null)
		{
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		return new  ResponseEntity<Void>(HttpStatus.OK);
		}
	*/
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session) {
		System.out.println("entering logout middleware");
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		User user=userDAO.getUser(email);
		user.setOnline(false);
		userDAO.updateUser(user);
		session.removeAttribute("loggedInUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
		/*return new ResponseEntity<String>("{\"message\":\"Sucess\"}",HttpStatus.OK);*/
		
	}

}
