package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.JobDAO;
import com.niit.DAO.UserDAO;
import com.niit.models.ErrorClazz;
import com.niit.models.Job;
import com.niit.models.User;

@RestController
public class JobController {

	
	@Autowired
	private JobDAO jobDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		
		String email=(String)session.getAttribute("loggedInUser");
		
		// CHECK FOR AUTHENTICATION
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		
		/*String email="sheetal@gmail.com";*/
		// CHECK FOR AUTHORISATION
		User user=userDAO.getUser(email);
		if(!user.getRole().equals("ADMIN")) {
			ErrorClazz errorClazz=new ErrorClazz(5, "Access Denied....You are not authorised to post job");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		try {
			job.setPostedOn(new Date());
		jobDAO.saveJob(job);
		
		}
		catch(Exception e) {
			
			ErrorClazz errorClazz=new ErrorClazz(6, "Unable to post job details");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		
		// CHECK FOR AUTHENTICATION
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		
		List<Job> jobs=jobDAO.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		
	}
	
}
