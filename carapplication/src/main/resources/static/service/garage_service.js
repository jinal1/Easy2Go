'use strict';

app1.factory('GarageService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8089/saveGarage';
    var REST_SERVICE_URIL='http://localhost:8089/garage';
var garageowner={};
    var factory = {
        
        createGarage: createGarage,
        fetchAllGarages:fetchAllGarages
        
    };

    return factory;
    function createGarage(garageowner) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI,garageowner)
            .then(
            function (response) {
                deferred.resolve(response.data);
                
               window.location="index.html";
                
            },
            function(errResponse){
            	// window.location="index.html";
                console.error('Error while creating User');
                deferred.reject(errResponse);
                
            }
        );
        return deferred.promise;
    }
    function fetchAllGarages()
{
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
}]);
