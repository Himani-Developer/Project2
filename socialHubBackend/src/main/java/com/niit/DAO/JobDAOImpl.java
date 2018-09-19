package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.Job;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean saveJob(Job job) {
	Session session = sessionFactory.getCurrentSession();
	session.save(job);
	return true;
	}

	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
		List<Job> jobs=query.list();
		return jobs;
	}

}
