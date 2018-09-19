/**
 * 
 */


app.controller("BlogInDetailCtrl",function($scope,$location,BlogPostService,$routeParams,$sce){

	var id=$routeParams.id
	
	BlogPostService.getBlog(id).then(
			function(response){
		
		$scope.blogPost=response.data  //BlogPost object
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		
		if(response.status==401)
			$location.path("/login")
	})
	
	$scope.approveBlogPost=function(blogPost){
		BlogPostService.approveBlogPost(blogPost).then(function(response){
			$location.path("/blogswaitingforapproval")
		},function(response){
			if(response.status==401)
				$location.path("/login")
		})
	}
	
})