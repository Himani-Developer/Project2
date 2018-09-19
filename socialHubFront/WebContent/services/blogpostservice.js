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
		
	blogPostService.getBlogsApproved=function(){
		var url=BASE_URL+"/getblogs"
		return $http.get(url)
	}
	
	blogPostService.getBlog=function(id){
		return(BASE_URL+"/getblog/"+id)
	}
	
	blogPostService.getBlogsWaitingForApproval=function(){
		var url=BASE_URL+"/blogswaitingforapproval"
		return $http.get(url)
	}
	
	blogPostService.approveBlogPost=function(blogPost){
		return $http.put(BASE_URL+"/approveblogpost",blogPost)
	}
		
		
		return blogPostService;
})