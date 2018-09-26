package com.niit.DAO;

import java.util.List;

import com.niit.models.Notification;

public interface NotificationDAO {

	public boolean addNotification(Notification notification);
	public List<Notification> getNotificationNotViewed(String email);
	public Notification getNotification(int notification_id);
	public boolean updateNotification(int notification_id);
}
