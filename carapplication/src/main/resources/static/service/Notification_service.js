app1.factory('notificationservice', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8089/donotification';

    var factory = {
        
        createNotification: createNotification
        
    };

    return factory;
    function createNotification() {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI)
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




}]);