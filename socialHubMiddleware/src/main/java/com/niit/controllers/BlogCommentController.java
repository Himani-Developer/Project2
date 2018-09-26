package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogCommentDAO;
import com.niit.DAO.UserDAO;
import com.niit.models.BlogComment;
import com.niit.models.BlogPost;
import com.niit.models.ErrorClazz;
import com.niit.models.User;

@RestController
public class BlogCommentController {

	@Autowired
	private BlogCommentDAO blogCommentDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogPost blogPost,@RequestParam String commentTxt,HttpSession session){
		
		String email=(String)session.getAttribute("loggedInUser");
		
		// String email="himani@gmail.com";
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		BlogComment blogComment=new BlogComment();
		blogComment.setBlogPost(blogPost);
		
		User user=userDAO.getUser(email);
		blogComment.setCommentedBy(user);
		
		blogComment.setCommentedOn(new Date());
		blogComment.setCommentText(commentTxt);
		
		blogCommentDAO.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/getcomments/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		
		// String email="himani@gmail.com";
		if(email==null) {
			
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogComment> comments=blogCommentDAO.getBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(comments,HttpStatus.OK);
	}
}
