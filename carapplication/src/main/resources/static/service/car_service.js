'use strict';

angular.module('myApp').factory('loginService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8089/saveCar';

    var factory = {
        
        createUser: createUser,
        
    };

    return factory;
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
                window.location="car1.html";
                
            },
            function(errResponse){
            	 window.location="index.html";
                console.error('Error while creating User');
                deferred.reject(errResponse);
                
            }
        );
        return deferred.promise;
    }




}]);
