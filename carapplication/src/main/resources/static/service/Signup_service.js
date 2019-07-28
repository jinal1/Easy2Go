'use strict';

App.factory('SignupService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8089/saveUser';
    var REST_SERVICE_URIL='http://localhost:8089/user';
    var REST_SERVICE_URIL1='http://localhost:8089/getUserName';
    var REST_SERVICE_URIL2='http://localhost:8089/updateUser';
    
var user={};
var userroles={};
    var factory = {
        
        createUser: createUser,
        fetchAllUsers:fetchAllUsers,
        fetchCurrentUser:fetchCurrentUser,
        updateuser:updateuser,
        
    };

    return factory;
    
    
    function updateuser(user) {
        var deferred = $q.defer();
        console.log("in service");
        $http.post(REST_SERVICE_URIL2,user)
            .then(
            function (response) {
            	console.log("in js");
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function fetchCurrentUser() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URIL1)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URIL)
            .then(
            function (response) {
                deferred.resolve(response.data);
               // console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function createUser(user) {
        var deferred = $q.defer();
        console.log(user);
        $http.post(REST_SERVICE_URI,user)
            .then(
            function (response) {
                deferred.resolve(response.data);
                
             //  window.location="index.html";
                
            },
            function(errResponse){
            	// window.location="index.html";
                console.error('Error while creating User');
                deferred.reject(errResponse);
                
            }
        );
        return deferred.promise;
    }




}]);
