/**
 * 
 */

app.factory("JobService",function($http){
	var jobService={}
	
	var BASE_URL="http://localhost:8081/socialHubMiddleware"
	
		jobService.addjob=function(job){
		var url=BASE_URL+"/addjob"
		return $http.post(url,job)
	}
	
	
	jobService.getalljobs=function(){
		var url=BASE_URL+"/getalljobs"
		return $http.get(url)
	}
	return jobService;
})