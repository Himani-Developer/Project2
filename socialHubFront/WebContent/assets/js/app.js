/**
 * 
 */

var app=angular.module("app",['ngRoute','ngCookies'])

app.config(function($routeProvider){
	$routeProvider
	.when('/registration',{controller:'UserCtrl',templateUrl:'views/registrationform.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
	.when('/updateprofile',{controller:'UserCtrl',templateUrl:'views/updateprofile.html'})
	.when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobform.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'views/listofjobs.html'})
	.when('/addblogpost',{controller:'BlogPostCtrl',templateUrl:'views/blogform.html'})
	.when('/getblogs',{controller:'BlogPostCtrl',templateUrl:'views/listofblogsapproved.html'})
	.when('/getblog/:id',{controller:'BlogInDetailCtrl',templateUrl:'views/blogindetail.html'})
	.when('/blogswaitingforapproval',{controller:'BlogPostCtrl',templateUrl:'views/listofblogswaitingforapproval.html'})
	.when('/getblogswaitingforapproval/:id',{controller:'BlogInDetailCtrl',templateUrl:'views/blogapprovalform.html'})
	.when('/home',{controller:'HomeCtrl',templateUrl:'views/home.html'})
	.when('/getnotification/:notification_id',{controller:'NotificationCtrl',templateUrl:'views/notificationDetails.html'})
	.when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'views/suggesteduserslist.html'})
	.when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'views/pendingrequestlist.html'})
	.when('/listoffriends',{controller:'FriendCtrl',templateUrl:'views/friendlist.html'})
	.when('/uploadprofilepicture',{templateUrl:'views/uploadprofilepicture.html'})
	
	
	.otherwise({templateUrl:'views/home.html'})
})


// restore details of user
app.run(function($rootScope,$cookieStore,UserService,$location){
	
	if($rootScope.user==undefined)    //reloading index 
		{
		$rootScope.user=$cookieStore.get('userDetails')
		}
	
	$rootScope.logout=function(){
		alert("Entering logout function ")
		UserService.logout().then(function(response){
			
			delete $rootScope.user
			$cookieStore.remove('userDetails')
			$location.path('/login')
			
		},function(response){
			if($rootScope.user!=undefined)
			delete $rootScope.user
			$cookieStore.remove('userDetails')
			$location.path('/login')
		})
	}
	
	
})

