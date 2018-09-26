/**
 * NotificationCtrl
 */

app.controller("NotificationCtrl",function($scope,NotificationService,$routeParams,$rootScope){
	
	var id=$routeParams.notification_id
	
	NotificationService.getNotification(id).then(function(response){
		$scope.notification=response.data  // select * from notification where notification_id=?
	},function(response){
		if(response.status==401)
			$location.path("/login")
	})
	
	
	NotificationService.updateNotification(id).then(function(response){
		getNotificationNotViewed()
	},function(response){
		if(response.status==401)
			$location.path("/login")
	})
	
	
	function getNotificationNotViewed(){
		NotificationService.getNotificationNotViewed().then(function(response){
			$rootScope.notifications=response.data
			$rootScope.notificationCount=$rootScope.notifications.length
		},function(response){
			if(response.status==401)
				$location.path("/login")
		})
	}
	
})