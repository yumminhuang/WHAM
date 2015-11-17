// create the module and name it scotchApp
var whamApp = angular.module('whamApp', [ 'ngRoute', 'ngMap', 'ui.bootstrap',
		'angularUtils.directives.dirPagination' ]);

// configure our routes
whamApp.config(function($routeProvider) {

	$routeProvider

	// route for the home page
	.when('/home', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	// route for the about page
	.when('/about', {
		templateUrl : 'pages/about.html',
		controller : 'aboutController'
	})

	// route for the contact page
	.when('/contact', {
		templateUrl : 'pages/contact.html',
		controller : 'contactController'
	})

	.when('/', {
		templateUrl : 'pages/landing-page.html',
		controller : 'landingController'
	})

	.when('/login', {
		templateUrl : 'pages/login.html',
		controller : 'loginController'
	})

	.when('/register', {
		templateUrl : 'pages/register.html',
		controller : 'registrationController'
	})

	.when('/advancedSearch/:category/:city', {
		templateUrl : 'pages/advancedSearch.html',
		controller : 'advancedSearchController'
	})

	.when('/search/:query', {
		templateUrl : 'pages/search.html',
		controller : 'basicSearchController'
	});
});

whamApp.controller('loginController', function($scope, $uibModal) {

});

whamApp.controller('basicSearchController', function($scope, $rootScope, $http) {
	$scope.query = $rootScope.userQuery;

	var req = {
			method : 'GET',
			url : 'http://localhost:8080/WHAM/api/search',
			headers : {
				keywords: $scope.query
			}
		};
	$http(req).then(function(response) {
		$scope.currentPage = 1;
		$scope.pageSize = 10;
		$scope.records = response.data.records;
	});

	$scope.$on('mapInitialized', function(event, map) {
		$scope.objMapa = map;
	});
	$scope.showInfoWindow = function(event, record) {
		var infowindow = new google.maps.InfoWindow();
		var center = new google.maps.LatLng(record.latitude, record.longitude);

		infowindow.setContent(record.name);

		infowindow.setPosition(center);
		infowindow.open($scope.objMapa);
	};
});

whamApp.controller('advancedSearchController', function($http, $scope, $rootScope) {

	var category = $rootScope.selectedCategory;
	var city = $rootScope.selectedCity;

	var req = {
		method : 'GET',
		url : 'http://localhost:8080/WHAM/api/search',
		headers : {
			category : category,
			location : city
		}
	};
	$http(req).then(function(response) {
		$scope.currentPage = 1;
		$scope.pageSize = 10;
		$scope.records = response.data.records;
	});

	$scope.$on('mapInitialized', function(event, map) {
		$scope.objMapa = map;
	});
	$scope.showInfoWindow = function(event, record) {
		var infowindow = new google.maps.InfoWindow();
		var center = new google.maps.LatLng(record.latitude, record.longitude);

		infowindow.setContent(record.name);

		infowindow.setPosition(center);
		infowindow.open($scope.objMapa);
	};

});

whamApp.controller('registrationController', function($scope) {
	$scope.saveUserData = function(isValid) {
		if (isValid) {
			alert('valid form');
		}
	}
});

whamApp.controller('mainController', function($scope, $uibModal) {

	$scope.message = 'Look! I am a home page.';
	$scope.locations = [ {
		'name' : 'India',
		'latitude' : '23.29',
		'longitude' : '79.2'
	}, {
		'name' : 'India - 1',
		'latitude' : '27.29',
		'longitude' : '73.2'
	}, {
		'name' : 'India - 2',
		'latitude' : '26.29',
		'longitude' : '76.2'
	} ];
});

whamApp.controller('landingController', function($scope, $http) {
	$scope.categories = [ {
		value : '1',
		text : 'Music'
	}, {
		value : '2',
		text : 'Arts'
	}, {
		value : '3',
		text : 'Food & Drink'
	}, {
		value : '4',
		text : 'Health'
	}, {
		value : '5',
		text : 'Fashion'
	}, {
		value : '6',
		text : 'Science & Tech'
	} ];

	$scope.getCurrentUserLocation = function() {
		navigator.geolocation.getCurrentPosition(function(position) {
			$scope.currentLatitude = position.coords.latitude;
			$scope.currentLongitude = position.coords.longitude;
		});
	};

	var req = {
			method : 'GET',
			url : 'http://localhost:8080/WHAM/api/search',
			headers : {
				latitude : $scope.currentLatitude,
				longitude : $scope.currentLongitude,
				popular: true
			}
		};
		$http(req).then(function(response) {
			console.log($scope.currentLongitude);
			$scope.currentPage = 1;
			$scope.pageSize = 10;
			$scope.records = response.data.records;
			console.log(response.data.records);
		});

		$scope.$on('mapInitialized', function(event, map) {
			$scope.objMapa = map;
		});
		$scope.showInfoWindow = function(event, record) {
			var infowindow = new google.maps.InfoWindow();
			var center = new google.maps.LatLng(record.latitude, record.longitude);

			infowindow.setContent(record.name);

			infowindow.setPosition(center);
			infowindow.open($scope.objMapa);
		};


});

whamApp.controller('searchController', function($scope, $location, $http, $rootScope) {

	$scope.search = function(query) {
		$rootScope.userQuery = query;
		$location.path('/search/' + query);
	};

	$scope.advancedSearch = function(category) {
		$rootScope.selectedCategory = category.text;
		$rootScope.selectedCity = $scope.city;

		var myEl = angular.element(document.querySelector('.dropdown'));
		if (myEl.hasClass('open')) {
			myEl.removeClass('open');
		}
		$location.path('/advancedSearch/' + category.text + '/' + $scope.city);
	}
});

whamApp.controller('contactController', [ '$scope', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
} ]);

whamApp.controller('aboutController', function($scope) {
	$scope.message = 'Look! I am an about page.';
});
