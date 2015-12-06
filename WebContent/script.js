// create the module and name it scotchApp
var whamApp = angular.module('whamApp',
		[ 'ngRoute', 'ngMap', 'ui.bootstrap',
				'angularUtils.directives.dirPagination', 'base64',
				'td.easySocialShare', 'angularSpinner']);

// configure our routes
whamApp.config(function($routeProvider, $httpProvider) {

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
	})

	.when('/eventDetails/:eventId', {
		templateUrl : 'pages/eventDetail.html',
		controller : 'eventDetailsController'
	})

	.when('/profile', {
		templateUrl : 'pages/profile.html',
		controller : 'profileController'
	})

	.when('/preferences', {
		templateUrl : 'pages/preferences.html',
		controller : 'preferencesFormController'
	})

	.when('/stuff', {
		templateUrl : 'pages/stuff.html',
		controller : 'tabsController'
	})
	
	.when('/myEvents', {
		templateUrl : 'pages/myEvents.html',
		controller : 'myEventsController'
	})

	.otherwise({
		redirectTo : '/'
	});

	$httpProvider.interceptors.push('authInterceptor');
});

whamApp.run(function($rootScope, userService) {
	userService.getCurrentUser().then(function(data) {
		$rootScope.currentUser = data;
	}, function(error) {
		$rootScope.currentUser = null;
	});
});

whamApp.directive('googleplace', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, model) {
			var options = {
				types : [],
				componentRestrictions : {}
			};
			scope.gPlace = new google.maps.places.Autocomplete(element[0],
					options);

			google.maps.event.addListener(scope.gPlace, 'place_changed',
					function() {
						scope.$apply(function() {
							model.$setViewValue(element.val());
						});
					});
		}
	};
});

whamApp.directive('pwCheck', [ function() {
	return {
		require : 'ngModel',
		link : function(scope, elem, attrs, ctrl) {
			var firstPassword = '#' + attrs.pwCheck;
			elem.add(firstPassword).on('keyup', function() {
				scope.$apply(function() {
					var v = elem.val() === $(firstPassword).val();
					ctrl.$setValidity('pwmatch', v);
				});
			});
		}
	}
} ]);

whamApp.directive('checkImage', function($http) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            attrs.$observe('ngSrc', function(ngSrc) {
                $http.get(ngSrc).success(function(){
                }).error(function(){
                    element.attr('src', 'http://www.njstatelib.org/wp-content/uploads/2014/05/events_medium.jpg'); // set'); // set default image
                });
            });
        }
    };
});

whamApp.factory('authInterceptor', function() {
	function handleRequest(config) {
		var token = localStorage.getItem('WHAM_AUTH_TOKEN');

		if (token && isApiRequest(config.url)) {
			config.headers = config.headers || {};
			config.headers.Authorization = getBasicToken(token);
		}
		return config;
	}

	function isApiRequest(path) {
		return path.indexOf('/WHAM/api') == 0;
	}

	function getBasicToken(token) {
		return "Bearer " + token;
	}

	return {
		request : handleRequest
	};
});

whamApp.factory('userService', function($q, $http, $base64) {

	var login = function(email, password) {
		var defer = $q.defer();

		var token = $base64.encode(email + ':' + password);
		localStorage.setItem("WHAM_AUTH_TOKEN", token);

		$http.get('/WHAM/api/users/current').then(function(resp) {
			defer.resolve(resp.data);
		}, function(error) {
			localStorage.removeItem("WHAM_AUTH_TOKEN");
			defer.reject(error);
		});

		return defer.promise;
	};

	var logout = function() {
		localStorage.removeItem("WHAM_AUTH_TOKEN");
	};

	var getCurrentUser = function() {
		var defer = $q.defer();
		$http.get('/WHAM/api/users/current').then(function(resp) {
			defer.resolve(resp.data);
		}, function(error) {
			console.log(error);
			defer.reject(error);
		});

		return defer.promise;
	};

	return {
		login : login,
		getCurrentUser : getCurrentUser,
		logout : logout
	};
});

whamApp.controller('loginController', function($scope, $rootScope, userService,
		$location) {
	$scope.login = function() {
		var email = $scope.email;
		var password = $scope.password;
		userService.login(email, password).then(function(data) {
			$rootScope.currentUser = data;
			$location.path('/');
		}, function(error) {
			alert('Invalid Login');
		});
	};
});

whamApp.controller('myEventsController', function($scope, $http, $rootScope) {
	$scope.showSpinner = true;
	$scope.mySearchRecords = [];
	$scope.getMyEvents = function() {
		var req = {
				method : 'GET',
				url : '/WHAM/api/users/preferences'
			};
			$http(req).then(function(response) {
				$scope.userpreferences = response.data;
				navigator.geolocation.getCurrentPosition(function(position) {
					$scope.currentLatitude = position.coords.latitude;
					$scope.currentLongitude = position.coords.longitude;

					var req = {
						method : 'GET',
						url : '/WHAM/api/event/search',
						headers : {
							latitude : $scope.currentLatitude,
							longitude : $scope.currentLongitude,
							subcategory : $scope.userpreferences
						}
					};
					$http(req).then(function(response) {
						$scope.currentPage = 1;
						$scope.pageSize = 10;
						$scope.mySearchRecords = response.data.records;
						$scope.showSpinner = false;
						if($scope.mySearchRecords.length == 0) {
							$scope.noResults = true;
						}
					});
				});
			});
	};
	
	$scope.$on('mapInitialized', function(event, map) {
		$scope.objMapa = map;
	});

	$scope.showInfoWindow = function(event, record) {
		$rootScope.event = record;
		var infowindow = new google.maps.InfoWindow();
		var center = new google.maps.LatLng(record.latitude, record.longitude);

		var funcToCall = '<a target="_blank" href="#/eventDetails/' + record.id
				+ '">' + record.name + '</a>';

		infowindow.setContent(funcToCall);

		infowindow.setPosition(center);
		infowindow.open($scope.objMapa);
	};
	
});

whamApp.controller('profileController', function($scope, $rootScope, $http, $uibModal, userService) {
	$scope.updateUserData = function(userForm) {
		var nowUser = $rootScope.currentUser;
		$http({
			method : 'PUT',
			url : '/WHAM/api/users/updateuser',
			data : $.param(nowUser),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data) {
			var modalInstance = $uibModal.open({
				templateUrl : 'pages/profileSuccess.html',
				controller : profileSuccessController
			});
		});
	};
});

var profileSuccessController = function($location, $scope, $uibModalInstance) {
	$scope.goToHome = function(){
		$location.path('#');
		$scope.cancel();
	}
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss("cancel");
	};
};

whamApp.controller('eventDetailsController', function($rootScope, $routeParams,
		$scope, $http, $location) {
	$scope.event = [];
	var eventId = $routeParams.eventId;
	var req = {
		method : 'GET',
		url : '/WHAM/api/event/' + eventId,
		headers : {
			id : eventId
		}
	};

	$http(req).then(function(response) {
		$scope.event = response.data;
	});
});

whamApp.controller('advancedSearchController', function($http, $scope,
		$rootScope, $routeParams, $location, $base64, $window) {
	$scope.showSpinner = true;
	$scope.advancedSearchRecords = [];
	var category = $base64.decode($routeParams.category);
	var address = String($base64.decode($routeParams.city));
	$scope.currentPosition = address;
	var req = {
		method : 'GET',
		url : '/WHAM/api/event/search',
		headers : {
			category : category,
			address : address
		}
	};
	$http(req).then(function(response) {
		$scope.currentPage = 1;
		$scope.pageSize = 10;
		$scope.searchedCity = address;
		$scope.advancedSearchRecords = response.data.records;
		$scope.showSpinner = false;
		if($scope.advancedSearchRecords.length == 0) {
			$scope.noResults = true;
		}
	});

	$scope.$on('mapInitialized', function(event, map) {
		$scope.objMapa = map;
	});

	$scope.showInfoWindow = function(event, record) {
		$rootScope.event = record;
		var infowindow = new google.maps.InfoWindow();
		var center = new google.maps.LatLng(record.latitude, record.longitude);

		var funcToCall = '<a target="_blank" href="#/eventDetails/' + record.id
				+ '">' + record.name + '</a>';

		infowindow.setContent(funcToCall);

		infowindow.setPosition(center);
		infowindow.open($scope.objMapa);
	};
});

whamApp.controller('basicSearchController', function($scope, $rootScope, $http,
		$location, $routeParams, $base64, $compile, $parse, $window) {
	$scope.showSpinner = true;
	$scope.query = $base64.decode($routeParams.query);
	$scope.basicSearchRecords = [];
	navigator.geolocation.getCurrentPosition(function(position) {
		$scope.currentLatitude = position.coords.latitude;
		$scope.currentLongitude = position.coords.longitude;

		var req = {
			method : 'GET',
			url : '/WHAM/api/event/search',
			headers : {
				latitude : $scope.currentLatitude,
				longitude : $scope.currentLongitude,
				keywords : $scope.query
			}
		};
		$http(req).then(function(response) {
			$scope.currentPage = 1;
			$scope.pageSize = 10;
			$scope.basicSearchRecords = response.data.records;
			$scope.showSpinner = false;
			if($scope.basicSearchRecords.length == 0) {
				$scope.noResults = true;
			}
		});
	});

	$scope.$on('mapInitialized', function(event, map) {
		$scope.objMapa = map;
	});

	$scope.showInfoWindow = function(event, record) {
		$rootScope.event = record;
		var infowindow = new google.maps.InfoWindow();
		var center = new google.maps.LatLng(record.latitude, record.longitude);

		var funcToCall = '<a target="_blank" href="#/eventDetails/' + record.id
				+ '">' + record.name + '</a>';

		infowindow.setContent(funcToCall);

		infowindow.setPosition(center);
		infowindow.open($scope.objMapa);
	};
});

whamApp.controller('preferencesFormController', function($scope, $http, $uibModal) {

	$scope.formData = {};

	$scope.music = [ {
		name : 'Pop',
		value : 3007
	}, {
		name : 'Rock',
		value : 3011
	}, {
		name : 'Metal',
		value : 3012
	}, {
		name : 'Folk',
		value : 3013
	}, {
		name : 'Opera',
		value : 3017
	} ];
	
	$scope.travel = [ {
		name : 'Hiking',
		value : 9001
	}, {
		name : 'Rafting',
		value : 9002
	}, {
		name : 'Kayaking',
		value : 9003
	}, {
		name : 'Canoeing',
		value : 9004
	}, {
		name : 'Climbing',
		value : 9005
	} ];
	
	$scope.food = [ {
		name : 'Beer',
		value : 10001
	}, {
		name : 'Wine',
		value : 10002
	}, {
		name : 'Food',
		value : 10003
	}, {
		name : 'Spirits',
		value : 10004
	}, {
		name : 'Other',
		value : 10099
	} ];
	
	$scope.science = [ {
		name : 'Medicine',
		value : 2001
	}, {
		name : 'Biotech',
		value : 2003
	}, {
		name : 'Mobile',
		value : 2005
	}, {
		name : 'Robotics',
		value : 2007
	}, {
		name : 'Science',
		value : 2002
	} ];
	
	$scope.holiday = [ {
		name : 'Easter',
		value : 16002
	}, {
		name : 'Halloween/Haunt',
		value : 16004
	}, {
		name : 'Thanksgiving',
		value : 16005
	}, {
		name : 'Christmas',
		value : 16006
	}, {
		name : 'Channukah',
		value : 16007
	} ];
	
	$scope.selection = [];
	$scope.getPreferences = function() {
		var req = {
				method : 'GET',
				url : '/WHAM/api/users/preferences'
			};
			$http(req).then(function(response) {
				$scope.selection = response.data;
			});
	};
	
	$scope.toggleSelection = function toggleSelection(subCategoryId) {
		var idx = $scope.selection.indexOf(subCategoryId);
		if (idx > -1) {
			$scope.selection.splice(idx, 1);
		} else {
			$scope.selection.push(subCategoryId);
		}
	};
	
	$scope.savePreferences = function() {
		var data = {
				preferences: JSON.stringify($scope.selection)
		};
		$http({
			method : 'POST',
			url : '/WHAM/api/users/createpreference',
			data : $.param(data),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(data) {
			var modalInstance = $uibModal.open({
				templateUrl : 'pages/preferencesSaved.html',
				controller : preferencesSavedController
			});
		});
	};
});

var preferencesSavedController = function($scope, $uibModalInstance, $location) {

};




whamApp.controller('registrationController',
		function($scope, $http, $uibModal) {
			$scope.userForm = {};
			$scope.saveUserData = function(userForm) {

				// If form is invalid, return and let AngularJS show validation
				// errors.
				if (userForm.$invalid) {
					return;
				}

				$http({
					method : 'POST',
					url : '/WHAM/api/users',
					data : $.param($scope.newUser),
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded'
					}
				}).success(function(data) {
					var modalInstance = $uibModal.open({
						templateUrl : 'pages/loginRedirect.html',
						controller : loginRedirectController,
						backdrop : 'static'
					});
				}).error(function(error) {
					var modalInstance = $uibModal.open({
						templateUrl : 'pages/signUpError.html',
						controller : loginRedirectController
					});
				});
			}
		});

var loginRedirectController = function($scope, $uibModalInstance, $location) {

	$scope.goToLogin = function() {
		$location.path('login');
		$scope.cancel();
	}

	$scope.ok = function() {
		$uibModalInstance.close("ok");
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss("cancel");
	};
};

whamApp.controller('landingController', function($scope, $http, $rootScope,
		$location, userService) {
	$scope.categories = [ {
		value : '1',
		text : 'Music'
	}, {
		value : '2',
		text : 'Travel & Outdoor'
	}, {
		value : '3',
		text : 'Food & Drink'
	}, {
		value : '4',
		text : 'Seasonal & Holiday'
	}, {
		value : '6',
		text : 'Science & Technology'
	} ];

	$scope.getCurrentUserLocation = function() {
		navigator.geolocation.getCurrentPosition(function(position) {
			$rootScope.currentLatitude = position.coords.latitude;
			$rootScope.currentLongitude = position.coords.longitude;
		});
	};

	$scope.logout = function() {
		$rootScope.currentUser = null;
		userService.logout();
		$location.path('/');
	};

	$scope.showEventsAroundUser = function() {
		navigator.geolocation.getCurrentPosition(function(position) {
			$scope.currentLatitude = position.coords.latitude;
			$scope.currentLongitude = position.coords.longitude;
			var req = {
				method : 'GET',
				url : '/WHAM/api/event/search',
				headers : {
					latitude : $scope.currentLatitude,
					longitude : $scope.currentLongitude,
					popular : 'true'
				}
			};
			$http(req).then(function(response) {
				$scope.currentPage = 1;
				$scope.pageSize = 10;
				$scope.records = response.data.records;
			});
		});

		$scope.$on('mapInitialized', function(event, map) {
			$scope.objMapa = map;
		});
		$scope.showInfoWindow = function(event, record) {
			$rootScope.event = record;
			var infowindow = new google.maps.InfoWindow();
			var center = new google.maps.LatLng(record.latitude,
					record.longitude);

			var funcToCall = '<a target="_blank" href="#/eventDetails/'
					+ record.id + '">' + record.name + '</a>';

			infowindow.setContent(funcToCall);

			infowindow.setPosition(center);
			infowindow.open($scope.objMapa);
		};
	}
});

whamApp.controller('searchController', function($scope, $location, $http,
		$rootScope, $base64) {

	$scope.search = function(query) {
		$rootScope.userQuery = query;
		$location.path('/search/' + $base64.encode(query));
	};

	$scope.advancedSearch = function(category) {
		var myEl = angular.element(document.querySelector('.dropdown'));
		if (myEl.hasClass('open')) {
			myEl.removeClass('open');
		}
		$location.path('/advancedSearch/' + $base64.encode(category.text) + '/'
				+ $base64.encode($scope.city));
	}
});

whamApp.controller('contactController', [ '$scope', function($scope) {
	$scope.message = 'Contact us! JK. This is just a demo.';
} ])

whamApp.controller('tabsController', [ '$scope', function($scope) {
	$scope.tabs = [ {
		title : 'My Profile',
		url : 'pages/profile.html'
	}, {
		title : 'My Preferences',
		url : 'pages/preferences.html'
	} ];

	$scope.currentTab = 'pages/profile.html';

	$scope.onClickTab = function(tab) {
		$scope.currentTab = tab.url;
	}

	$scope.isActiveTab = function(tabUrl) {
		return tabUrl == $scope.currentTab;
	}

} ]);
