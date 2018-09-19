package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.DAO.BlogPostDAO;
import com.niit.DAO.UserDAO;
import com.niit.models.BlogPost;
import com.niit.models.ErrorClazz;
import com.niit.models.User;

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostDAO blogPostDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/addblogpost",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		
		String email=(String)session.getAttribute("loggedInUser");
		
		// String email="himani@gmail.com";
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		blogPost.setPostedOn(new java.util.Date());
		blogPost.setPostedBy(userDAO.getUser(email));
		
		try {
			blogPostDAO.addBlogPost(blogPost);
		}
		catch(Exception e) {
			ErrorClazz errorClazz=new ErrorClazz(6, "Unable to insert blogpost details..."+e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getblogs",method=RequestMethod.GET)
	public ResponseEntity<?> getApprovedBlogs(HttpSession session){
		String  email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogPost> approvedblogs=blogPostDAO.getApprovedBlogs();
		return new ResponseEntity<List<BlogPost>>(approvedblogs,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlog(@PathVariable int id,HttpSession session){
		String  email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		BlogPost blogPost=blogPostDAO.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/blogswaitingforapproval",method=RequestMethod.GET)
	public ResponseEntity<?> blogPostWaitingForApproval(HttpSession session){
	
		String  email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		//String email="sheetal@gmail.com";
		
		
	User user=userDAO.getUser(email);
	if(!user.getRole().equals("ADMIN")) {
		ErrorClazz errorClazz=new ErrorClazz(7, "Access Denied!!!");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	List<BlogPost> blogPosts=blogPostDAO.getBlogsWaitingForApproval();
	return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/approveblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session ){
		String  email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		//String email="sheetal@gmail.com";
		
		User user=userDAO.getUser(email);
		if(!user.getRole().equals("ADMIN")) {
			ErrorClazz errorClazz=new ErrorClazz(7, "Access Denied!!!");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		// updating blogpost
		blogPost.setApprovalStatus(true);
		blogPostDAO.updateBlogPost(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		
	}
	
	
}
