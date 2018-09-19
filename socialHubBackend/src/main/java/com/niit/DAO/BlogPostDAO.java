package com.niit.DAO;

import java.util.List;

import com.niit.models.BlogPost;

public interface BlogPostDAO {
	
	public boolean addBlogPost(BlogPost blogPost);
    public List<BlogPost> getApprovedBlogs();
    public List<BlogPost> getBlogsWaitingForApproval();
    public BlogPost getBlogPost(int id);
    public boolean updateBlogPost(BlogPost blogPost); 
    public boolean deleteBlogPost(BlogPost blogPost);
    public boolean incLikes(int id);
    public boolean dislikes(int id);
}
