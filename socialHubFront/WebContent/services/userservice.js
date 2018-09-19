/**
 * 
 */

app.factory('UserService',function($http){
	
	var userService={}
	var BASE_URL="http://localhost:8081/socialHubMiddleware"
		
		userService.registration=function(user){
		var url=BASE_URL+"/registration"
		return $http.post(url,user)
	}
	
	userService.login=function(user){
		var url=BASE_URL+"/login"
		return $http.put(url,user)
	}	
	
	
	userService.logout=function(){
		
		var url=BASE_URL+"/logout"
		return $http.put(url)
	}
	
	
	userService.updateprofile=function(user){
		var url=BASE_URL+"/updateprofile"
		return $http.put(url,user)
	}
	
	/*userService.getalljobs=function(){
		
		var url=BASE_URL+"/getalljobs"
		return $http.get(url)
	}
	*/
	
	return userService;
})