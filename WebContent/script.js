// create the module and name it scotchApp
var whamApp = angular.module('whamApp', ['ngRoute', 'ngMap', 'ui.bootstrap', 'angularUtils.directives.dirPagination']);


// configure our routes
whamApp.config(function($routeProvider) {

    $routeProvider

        // route for the home page
        .when('/home', {
            templateUrl : 'pages/home.html',
            controller  : 'mainController'
        })

        // route for the about page
        .when('/about', {
            templateUrl : 'pages/about.html',
            controller  : 'aboutController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        })

        .when('/', {
            templateUrl : 'pages/landing-page.html',
            controller  : 'landingController'
        })

        .when('/login', {
            templateUrl : 'pages/login.html',
            controller  : 'loginController'
        })

        .when('/register', {
            templateUrl : 'pages/register.html',
            controller  : 'registrationController'
        })

        .when('/advancedSearch', {
            templateUrl : 'pages/advancedSearch.html',
            controller  : 'advancedSearchController'
        })

        .when('/search', {
            templateUrl : 'pages/search.html',
            controller  : 'searchController'
        });
});

whamApp.controller('loginController', function($scope, $uibModal) {


});

whamApp.controller('advancedSearchController', function($http, $scope) {
	var keywords = "live,music"; 
    var req = { 
        method: 'GET', 
        url: 'http://localhost:8080/WHAM/api/search', 
        headers: { 
            longitude: "42.1", 
            latitude: "33.1", 
            popular: "true" 
        } 
    }; 
    $http(req).then(function(response) { 
        $scope.currentPage = 1; 
        $scope.pageSize = 10; 
        $scope.records = response.data.records; 
    }); 

});

whamApp.controller('registrationController', function($scope) {
    $scope.saveUserData = function (isValid) {
        if(isValid) {
            alert('valid form');
        }
    }
});

whamApp.controller('mainController', function($scope, $uibModal) {

    $scope.message = 'Look! I am a home page.';
    $scope.locations = [
        {
            'name': 'India',
            'latitude': '23.29',
            'longitude' : '79.2'
        },
        {
            'name': 'India - 1',
            'latitude': '27.29',
            'longitude' : '73.2'
        },
        {
            'name': 'India - 2',
            'latitude': '26.29',
            'longitude' : '76.2'
        }
    ];
});

whamApp.controller('landingController', function($scope, $uibModal) {

    $scope.getCurrentUserLocation = function () {
        navigator.geolocation.getCurrentPosition(function(position) {
            alert(position.coords.latitude + ' ' + position.coords.longitude);
        });
    };

    $scope.message = 'Look! I am a landing page.';
    $scope.locations = [
        {
            'name': 'India',
            'latitude': '23.29',
            'longitude' : '79.2'
        },
        {
            'name': 'India - 1',
            'latitude': '27.29',
            'longitude' : '73.2'
        },
        {
            'name': 'India - 2',
            'latitude': '26.29',
            'longitude' : '76.2'
        }
    ];
});



whamApp.controller('searchController', function($scope, $location, $http) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
    $scope.locations = [
        {
            'name': 'India',
            'latitude': '23.29',
            'longitude' : '79.2'
        },
        {
            'name': 'India - 1',
            'latitude': '27.29',
            'longitude' : '73.2'
        },
        {
            'name': 'India - 2',
            'latitude': '26.29',
            'longitude' : '76.2'
        }
    ];

    $scope.search = function () {
        console.log('button click');
        $location.path('/search');
    };

    $scope.advancedSearch = function() {
        var myEl = angular.element( document.querySelector('.dropdown'));
        if(myEl.hasClass('open')) {
            myEl.removeClass('open');
        }
        $location.path('/advancedSearch');
    }
});

whamApp.controller('contactController', ['$scope', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
}]);

whamApp.controller('aboutController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});





/*var modalInstance = $uibModal.open({
 animation: $scope.animationsEnabled,
 templateUrl: 'pages/login.html',
 controller: 'ModalInstanceCtrl',
 backdrop: 'true',
 resolve: {
 items: function () {
 return $scope.items;
 }
 }
 });

 modalInstance.result.then(function (selectedItem) {
 $scope.selected = selectedItem;
 }, function () {
 });*/


/*$scope.toggleAnimation = function () {
 $scope.animationsEnabled = !$scope.animationsEnabled;
 };*/



/*whamApp.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

 $scope.ok = function () {
 $uibModalInstance.close($scope.selected.item);
 };

 $scope.cancel = function () {
 $uibModalInstance.dismiss('cancel');
 };
 });*/