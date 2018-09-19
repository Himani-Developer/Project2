/**
 * 
 */

app.controller("JobCtrl",function($scope,JobService,$location){
	$scope.showJob=false
	// Data is from View to Controller
	$scope.addjob=function(job){
		JobService.addjob(job).then(function(response){
			
			alert("Successfully added a job");
			$location.path('/getalljobs')
		},function(response){
			if(response.data.errorCode==4)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
	//Data is from Controller to View
	//Statement which will get executed always
	JobService.getalljobs().then(function(response){
		$scope.jobs=response.data
		
	},function(response){
		if(response.status==401)
			$location.path("/login")
		
	})
	
	
	$scope.showJobDetails=function(id){
		$scope.id=id //job id
		$scope.showJob=!$scope.showJob 
	}
	
})