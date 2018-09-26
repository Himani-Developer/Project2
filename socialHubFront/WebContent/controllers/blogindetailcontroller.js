/**
 * 
 */


app.controller("BlogInDetailCtrl",function($scope,$location,BlogPostService,$routeParams,$sce){

	
	var id=$routeParams.id;
	$scope.isRejected=false;
	console.log(id);
	BlogPostService.getBlog(id).then(
			function(response){
		
		$scope.blogPost=response.data  //BlogPost object
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		
		if(response.status==401)
			$location.path("/login")
	})
	
	
	$scope.inclike=function(id){
		BlogPostService.inclike(id).then(function(response){
			$scope.blogPost=response.data
			$location.path("/getblog/"+$scope.blogPost.id)
		},function(response){
			
			$scope.error=response.data
		})
	}
	
	$scope.incdislike=function(id){
		BlogPostService.incdislike(id).then(function(response){
			$location.path("/getblog/:id")
		},function(response){
			
			$scope.error=response.data
		})
	}
	
	
	
	
	
	$scope.approveBlogPost=function(blogPost){
		BlogPostService.approveBlogPost(blogPost).then(function(response){
			$location.path("/blogswaitingforapproval")
		},function(response){
			if(response.status==401)
				$location.path("/login")
		})
	}
	
	
	$scope.rejectBlogPost=function(blogPost){
		console.log(blogPost)
		BlogPostService.rejectBlogPost(blogPost,$scope.rejectionReason).then(function(response){
			$location.path('/blogswaitingforapproval')
		},function(response){
			if(response.status==401)
				$location.path("/login")
		})
	}
	
	$scope.showTextArea=function(){
		$scope.isRejected=!$scope.isRejected
	}
	
	
	$scope.addComment=function(blog,commentTxt){
		if(commentTxt=="" || commentTxt==undefined )
			$scope.error='Please enter some comments..do not leave it empty'
				
			else
		BlogPostService.addComment(blog,commentTxt).then(function(response){
			alert('Comment added Successfully')
			$scope.commentTxt=""
			$scope.error=""
				$scope.blogComment=response.data
				
		},function(response){
			if(response.status==401)
				$location.path("/login")
		})
	}
	
	
	$scope.getBlogComments=function(blogPostId){
		BlogPostService.getBlogComments(blogPostId).then(function(response){
			$scope.blogComments=response.data
		},function(response){
			
		})
	}
	
	
})