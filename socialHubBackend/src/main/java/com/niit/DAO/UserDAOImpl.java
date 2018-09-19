package com.niit.DAO;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl() {
		System.out.println("UserDAOImpl bean created");
	}
	
	public boolean registration(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		return true;
	}

	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from User where email=:email").setParameter("email", email);
		
		
		User user=(User) query.uniqueResult();
		if(user!=null) {  // duplicate email
			return false;
		}else {// unique email address
			return true;
		}
	}

	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=:email and password=:password").
															setParameter("email", user.getEmail()).
																setParameter("password", user.getPassword());
		return (User)query.uniqueResult();
	}

	public boolean updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		return true;
	}

	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=session.get(User.class, email);
		return user;
	}

}
