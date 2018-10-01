package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.DAO.ProfilePictureDAO;
import com.niit.models.ErrorClazz;
import com.niit.models.ProfilePicture;

@RestController
public class ProfilePictureController {

	@Autowired
	private ProfilePictureDAO profilePictureDAO;
	
	@RequestMapping(value="/uploadprofilepicture",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(HttpSession session,@RequestParam CommonsMultipartFile image ){
		String email=(String)session.getAttribute("loggedInUser");
		
		// CHECK FOR AUTHENTICATION
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(4, "Unauthorised access...PLs login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setEmail(email);
		profilePicture.setImage(image.getBytes());
		profilePictureDAO.uploadProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		
	}
	
	
	
}
