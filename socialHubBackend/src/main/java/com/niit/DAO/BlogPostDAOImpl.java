package com.niit.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.BlogPost;

@Repository
@Transactional
public class BlogPostDAOImpl implements BlogPostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);
		return true;
	}

	public List<BlogPost> getApprovedBlogs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approvalStatus=true");
		List<BlogPost> blogPosts=query.list();
		return blogPosts;
		
	}

	public List<BlogPost> getBlogsWaitingForApproval() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approvalStatus=false");
		List<BlogPost> blogPosts=query.list();
		return blogPosts;
	}

	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=session.get(BlogPost.class, id);
		return blogPost;
	}

	public boolean updateBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.update(blogPost);
		return true;
	}

	public boolean deleteBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(blogPost);
		return true;
	}

	public boolean incLikes(int id) {
		try {
			BlogPost blogPost=this.getBlogPost(id);
			blogPost.setLikes(blogPost.getLikes()+1);
			sessionFactory.getCurrentSession().update(blogPost);
			return true;
		}catch(Exception e) {
			System.out.println("Exception Arised:"+e);
			return false;
		}
		
	}

	public boolean dislikes(int id) {
		try {
			BlogPost blogPost=this.getBlogPost(id);
			blogPost.setDislikes(blogPost.getDislikes()+1);
			sessionFactory.getCurrentSession().update(blogPost);
			
			return true;
		}catch(Exception e) {
			
		}
		return false;
	}

}
