package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.Friend;
import com.niit.models.User;

@Repository
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean addFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		return true;
	}

	public boolean deleteFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(friend);
		return true;
	}

	public boolean acceptFriendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.update(friend);
		return true;
	}

	public List<User> getSuggestedUsers(String email) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from User_Table where email in"
				+ "( select email from User_Table where email!=:email "
				+ " minus "
				+ " (select fromId_email from Friend_table where toId_email=:email "
				+ "   union "
				+ "  select toId_email from Friend_table where fromId_email=:email))";
		SQLQuery sqlQuery=session.createSQLQuery(queryString).setParameter("email", email);// sqlquery returns a record not object but hql returns object implicitly
		sqlQuery.addEntity(User.class);// for converting record to user type object
		return sqlQuery.list();
	}

	public List<Friend> getPendingRequests(String email) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend f where f.status=:'P' and f.toId.email=:email").setParameter("email", email);
		List<Friend> pendingRequests=query.list();
		return pendingRequests;
	}

	public List<User> listOfFriends(String email) {
		Session session=sessionFactory.getCurrentSession();
		//friend request from loggedin user to other users, and if it is accepted (status is 'A')
		Query query1=session.createQuery("select f.toId from Friend f where f.fromId.email=:email and f.status=:'A' ").setParameter("email", email);
		List<User> list1=query1.list();
		Query query2=session.createQuery("select f.fromId from Friend f where f.toId.email=:email and f.status=:'A'").setParameter("email", email);
		List<User> list2=query2.list();
		
		list1.addAll(list2);
		
		return list1;
	}
	

}
