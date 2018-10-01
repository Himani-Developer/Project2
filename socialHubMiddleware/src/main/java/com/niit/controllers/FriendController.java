package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.FriendDAO;
import com.niit.DAO.UserDAO;
import com.niit.models.ErrorClazz;
import com.niit.models.Friend;
import com.niit.models.User;

@RestController
public class FriendController {

@Autowired
private FriendDAO friendDAO;
@Autowired
private UserDAO userDAO;

@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
public ResponseEntity<?> getAllSuggestedUsers(HttpSession session){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	//String email="himani@gmail.com";
	
	List<User> suggestedUsers=friendDAO.getSuggestedUsers(email);
	return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	
}


//create new friend object [id,toId,fromId,status]
@RequestMapping(value="/friendrequest",method=RequestMethod.POST)
public ResponseEntity<?> addFriendRequest(@RequestBody User friendRequestToId,HttpSession session){
	
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	User fromId=userDAO.getUser(email);
	Friend friend=new Friend();
	friend.setFromId(fromId);
	friend.setToId(friendRequestToId);
	friend.setStatus('P');
	friendDAO.addFriendRequest(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	
}

@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
public ResponseEntity<?> getPendingRequests(HttpSession session){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	//String email="anika@gmail.com";
	List<Friend> pendingRequests=friendDAO.getPendingRequests(email);
	return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	
}

@RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
public ResponseEntity<?> acceptRequests(HttpSession session,@RequestBody Friend friend){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	friend.setStatus('A');
	friendDAO.acceptFriendRequest(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}

@RequestMapping(value="/deleterequest",method=RequestMethod.PUT)
public ResponseEntity<?> deleteRequests(HttpSession session,@RequestBody Friend friend){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	friendDAO.deleteFriendRequest(friend);
	return new ResponseEntity<Void>(HttpStatus.OK);
}

@RequestMapping(value="/listoffriends",method=RequestMethod.GET)
public ResponseEntity<?> listOffriends(HttpSession session){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null) {
		ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	List<User> listoffriends=friendDAO.listOfFriends(email);
	return new ResponseEntity<List<User>>(listoffriends,HttpStatus.OK);
	
}


}
