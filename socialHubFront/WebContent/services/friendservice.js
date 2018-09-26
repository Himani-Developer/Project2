/**
 * FriendService
 */

app.factory("FriendService",function($http){
	
	var friendService={}
	var BASE_URL="http://localhost:8081/socialHubMiddleware";
	
	
	friendService.getSuggestedUsers=function(){
		return $http.get(BASE_URL+"/suggestedusers")
	}
	
	
	return friendService;
})