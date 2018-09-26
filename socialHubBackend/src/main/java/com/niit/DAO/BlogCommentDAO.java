package com.niit.DAO;

import java.util.List;

import com.niit.models.BlogComment;

public interface BlogCommentDAO {

	public boolean addBlogComment(BlogComment blogComment);
	public List<BlogComment> getBlogComments(int blogPostId);
}
