/**
 * FriendService
 */

app.factory("FriendService",function($http){
	
	var friendService={}
	var BASE_URL="http://localhost:8081/socialHubMiddleware";
	
	
	friendService.getSuggestedUsers=function(){
		return $http.get(BASE_URL+"/suggestedusers")
	}
	
	friendService.sendFriendRequest=function(friendRequestToId){
		var url=BASE_URL+"/friendrequest"
		return $http.post(url,friendRequestToId)
	}
	
	friendService.getPendingRequests=function(){
		
		var url=BASE_URL+"/pendingrequests"
		return $http.get(url)
	}
	
	friendService.acceptRequest=function(pendingRequest){
		return $http.put(BASE_URL+"/acceptrequest",pendingRequest)
	}
	
	friendService.deleteRequest=function(pendingRequest){
		return $http.put(BASE_URL+"/deleterequest",pendingRequest)
	}
	
	friendService.listOfFriends=function(){
		return $http.get(BASE_URL+"/listoffriends")
	}
	
	
	return friendService;
})