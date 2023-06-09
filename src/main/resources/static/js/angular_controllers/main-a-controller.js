
app.controller('loginController',function($scope, $window ,$rootScope,$location){
	
	$scope.doLogin = function(){
 
		$window.location="https://github.com/login/oauth/authorize?scope=user:&client_id=282ab694a7ee8a8b4e81";
	}
	
	if($rootScope.userSessionVO == null){
		$location.path('/');
	}


});


app.controller('survay-controller',function($scope, $window ,$rootScope,$location,mainService){
	
	$scope.rec={};
	$scope.submitSurvay = function(){
		//alert(document.getElementById('userId').value);
		var input = {};
		input['response'] = angular.toJson($scope.rec);
		input['userId'] = document.getElementById('userId').value;
		input = angular.toJson(input);

		mainService.callPostRestAPI("/chatbot/submitSurvayBio/", input).then(function (data) {
			alert("Thanks for submitting survey");
			$window.location.href = 'http://204.76.187.58:8080/OnTimeRecommend/#!/';
		});
	}


});
app.controller('logoutController', function($scope, $window, $http) {

	$scope.logout = function(cat_name){
		
		$http.get("login/logoutUser").then(
	      function successCallback(response) {
	    	  $window.location.reload()
	      },
	      function errorCallback(response) {
	        console.log("POST-ing of data failed");
	   });
		 
	}
	
});


app.controller('menuController', function($scope, $location, $rootScope, mainService) {
	$scope.menu = {};

	if($location.$$path != '/' ){
		var path = $location.$$path.substring(1,$location.$$path.length);
		$scope.menu[path]='active';
		$rootScope.menu_name=path;
	}else{
		$scope.menu['/']='active';
		$rootScope.menu_name='Home';
	}
	
	// $scope.getAllRecommender = function(){
	// 	var input = {};
	// 	input['clientId'] = 4
	// 	input = angular.toJson(input);
	
	// 	mainService.callPostRestAPI("recommendation-service/getRecommenderListClient/", input).then(function (data) {
	// 		console.log(data);
	// 		$scope.recList = data;
	// 	});
	// }
	
	// if(!document.getElementById("userSessionVO")){
	// 	$scope.getAllRecommender();
	// }
	
	
	$scope.loadMenu = function(menu_name,level){
		$scope.menu = {};
		$location.path(menu_name);
		$scope.menu[menu_name]='active';
		
		if(menu_name == '/'){
			$rootScope.menu_name = 'Home';
		}else if (menu_name.includes('filter_page')){
			const queryString = window.location.search;
			const urlParams = new URLSearchParams(queryString);
			const username = urlParams.get('username')
			console.log(username);
			if(username == null){
				window.location.href = "/Scholarteamfinder/login";
			}
			// window.history.pushState(null, null, "/Scholarteamfinder/login");
		}else{
			$rootScope.menu_name = menu_name;
		}
		
	}
	
	$scope.showDiv = function(){
		document.getElementById('chatbot').style.display="block"; 
	}

	$scope.setUserSession = function(){
		
		if(document.getElementById("userSessionVO")){
			var user_session = document.getElementById("userSessionVO").value;
			user_session = angular.fromJson(user_session);
			$rootScope.userSessionVO = user_session;
			
		}
	}
	$scope.setUserSession();
	
	
});


app.controller('mainPageController', function($scope,$location, mainService, $routeParams,recSystemService) {
	$scope.getPublicationNum = function(){
		mainService.callPostRestAPI("publication/getNumber/").then(function (data) {
			console.log(data);
			$scope.publicationNum = data;
			let test = parseInt($scope.publicationNum);
			let nf = new Intl.NumberFormat('en-US');
			$scope.publicationNum = nf.format(test);
			console.log($scope.publicationNum);
		});
	}

	$scope.getUsersNum = function(){
		mainService.callPostRestAPI("user/number/").then(function (data) {
			console.log(data);
			$scope.userNum = data;
		});
	}

	$scope.getPublicationNum();
	$scope.getUsersNum();
});
