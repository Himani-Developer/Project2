package com.niit.DAO;

import java.util.List;

import com.niit.models.Job;

public interface JobDAO {
	
	public boolean saveJob(Job job);
	public List<Job> getAllJobs();
}
