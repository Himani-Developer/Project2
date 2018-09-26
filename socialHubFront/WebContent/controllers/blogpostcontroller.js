/**
 * 
 */


app.controller("BlogPostCtrl",function($scope,$location,BlogPostService,$rootScope){
	
	$scope.addBlogPost=function(blogPost){
		BlogPostService.addBlogPost(blogPost).then(function(response){
			alert("Blogpost added successfully and it is waiting for approval")
			$location.path('/home')
			
		},function(response){
			//Not Authenticated
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
			
		})
	}
	
	
	
	//List of approved blogs
	function getBlogsApproved(){
		
		BlogPostService.getBlogsApproved().then(function(response){
			$scope.blogsApproved=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
		
	}
	
	
	function getBlogsWaitingForApproval(){
		BlogPostService.getBlogsWaitingForApproval().then(function(response){
			$scope.blogsWaitingForApproval=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	
	getBlogsApproved()     //function calling
	
	//function calling when user is ADMIN
	if($rootScope.user.role=='ADMIN')
	getBlogsWaitingForApproval()
	
	
})



