app.controller('ChatCtrl',function($scope,$rootScope,chatService){

	$scope.messages=[];
	$scope.message="";
	$scope.max=250;
	
	$scope.addMessage=function()
	{
		chatService.send($rootScope.user.firstname+":" +$scope.message);
		$scope.message="";
	};

	chatService.receive().then(null,null,function(message)
	{
		$scope.messages.push(message);
	});	

	
})