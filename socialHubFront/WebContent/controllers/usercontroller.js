/**
 * 
 */

app.controller("UserCtrl",function($scope,UserService,$location,$rootScope,$cookieStore){
	
	$scope.registration=function(user){
		
		UserService.registration(user).then(function(response){
			
			alert('Registration successful')
			$scope.user=response.data
			$location.path("/login")
			
		},function(response){
			$scope.error=response.data   // ErrorClazz in Json format
			
		})
	}
	
	
	$scope.login=function(user){
		UserService.login(user).then(function(response){
			
			$cookieStore.put('userDetails',response.data)
			$rootScope.user=response.data // user object
			$location.path("/home")
			
		},function(response){
			$scope.error=response.data
			
		})
	}
	
	
	
	
	
	
	$scope.updateprofile=function(user){
		UserService.updateprofile(user).then(function(response){
			
			$rootscope.user=response.data
			$cookieStore.put('userDetails',response.data)
			alert("Updated successfully")
			$location.path("/home")
			
		},function(response){
			
			if(response.status==401)
				$location.path("/login")
				
			$scope.error=response.data
		})
	}
	
	
	
	/*UserService.getalljobs().then(function(response){
		
		
	},function(response){
		$location.path("/login")
		
	})
	*/
	
})