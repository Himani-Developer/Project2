package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.NotificationDAO;
import com.niit.models.ErrorClazz;
import com.niit.models.Notification;

@RestController
public class NotificationController {

	@Autowired
	private NotificationDAO notificationDAO;
	
	@RequestMapping(value="/notifications",method=RequestMethod.GET)
	public ResponseEntity<?> getNotificationNotViewed(HttpSession session){
		
		String email=(String) session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		//String email="himani@gmail.com";
		
		List<Notification> notifications=notificationDAO.getNotificationNotViewed(email);
		return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/notification/{notification_id}",method=RequestMethod.GET)
	public ResponseEntity<?> getNotification(@PathVariable int notification_id,HttpSession session){
		String email=(String) session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		Notification notification=notificationDAO.getNotification(notification_id);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/updatenotification/{notification_id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateNotifications(@PathVariable int notification_id,HttpSession session ){
		String email=(String) session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		notificationDAO.updateNotification(notification_id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	
}
