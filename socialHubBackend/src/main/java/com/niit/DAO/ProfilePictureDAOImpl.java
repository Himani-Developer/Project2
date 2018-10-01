package com.niit.DAO;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.ProfilePicture;

@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean uploadProfilePicture(ProfilePicture profilePicture) {
		
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
		return true;
	}

	public ProfilePicture getProfilePicture(String email) {
		try {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=session.get(ProfilePicture.class, email);
		return profilePicture;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

}
