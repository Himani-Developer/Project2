/**
 * 
 */

app.factory("BlogPostService",function($http){
	
	var blogPostService={}
	var BASE_URL="http://localhost:8081/socialHubMiddleware"
		
		blogPostService.addBlogPost=function(blogPost){
		var url=BASE_URL+"/addblogpost"
		return $http.post(url,blogPost)
	}
	
	blogPostService.inclike=function(id){
		var url=BASE_URL+"/incrementLikes/"+id
		return $http.get(url)
	}
	
	blogPostService.incdislike=function(id){
		var url=BASE_URL+"/incrementDislikes/"+id
		return $http.get(url)
	}
		
	blogPostService.getBlogsApproved=function(){
		var url=BASE_URL+"/getblogs"
		return $http.get(url)
	}
	
	blogPostService.getBlog=function(id){
	
		return $http.get(BASE_URL+"/getblog/"+id)
	}
	
	blogPostService.getBlogsWaitingForApproval=function(){
		var url=BASE_URL+"/blogswaitingforapproval"
		return $http.get(url)
	}
	
	blogPostService.approveBlogPost=function(blogPost){
		return $http.put(BASE_URL+"/approveblogpost",blogPost)
	}
		
	
	blogPostService.rejectBlogPost=function(blogPost,rejectionReason){
		console.log(blogPost);
		return $http.put(BASE_URL+"/rejectblogpost?rejectionReason="+rejectionReason,blogPost)
	}
		
	
	blogPostService.getNotificationNotViewed=function(){
		return $http.get(BASE_URL+"/notifications")
		}		
	
	
	blogPostService.addComment=function(blog,commentTxt){
		return $http.post(BASE_URL+"/addblogcomment?commentTxt="+commentTxt,blog)
	}
	
	blogPostService.getBlogComments=function(blogPostId){
		return $http.get(BASE_URL+"/getcomments/"+blogPostId)
	}
	

	
	
		return blogPostService;
})