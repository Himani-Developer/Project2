/**
 *  FriendCtrl
 */

app.controller("FriendCtrl",function($scope,$location,FriendService){
	
	function getSuggestedUsers(){
	FriendService.getSuggestedUsers().then(function(response){
		$scope.suggestedUsers=response.data  // List<User>  [S in (A-(BUC))]
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	
	$scope.sendFriendRequest=function(friendRequestToId){
	FriendService.sendFriendRequest(friendRequestToId).then(function(response){
		alert('Freind Request Sent successfully')
		getSuggestedUsers()
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	
	FriendService.listOfFriends().then(function(response){
		$scope.friendLists=response.data
		
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	
	
	
	function getPendingRequests(){
		
		FriendService.getPendingRequests().then(function(response){
			$scope.pendingRequests=response.data   // List<Friend> pending list
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.acceptRequest=function(pendingRequest){
		FriendService.acceptRequest(pendingRequest).then(function(response){
			getPendingRequests()
		},function(resposne){
			if(response.status==401)
				$location.path('/login')

		})
	}
	
	
	$scope.deleteRequest=function(pendingRequest){
		FriendService.deleteRequest(pendingRequest).then(function(response){
			getPendingRequests()
		},function(response){
			
		})
	}
	
	
	getSuggestedUsers()
	getPendingRequests()
})