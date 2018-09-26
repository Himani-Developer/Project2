package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.Notification;

@Repository
@Transactional
public class NotificationDAOImpl implements NotificationDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean addNotification(Notification notification) {
		Session session=sessionFactory.getCurrentSession();
		session.save(notification);
		return true;
	}

	public List<Notification> getNotificationNotViewed(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notification where email=:email and viewed=0").setParameter("email", email);
		return query.list();
	}

	public Notification getNotification(int notification_id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=session.get(Notification.class, notification_id);
		
		return notification;
	}

	public boolean updateNotification(int notification_id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=session.get(Notification.class, notification_id);
		notification.setViewed(true);
		session.update(notification);
		return false;
	}

}
