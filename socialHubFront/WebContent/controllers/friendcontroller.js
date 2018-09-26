/**
 *  FriendCtrl
 */

app.controller("FriendCtrl",function($scope,$location,FriendService){
	
	FriendService.getSuggestedUsers().then(function(response){
		$scope.suggestedUsers=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	
})