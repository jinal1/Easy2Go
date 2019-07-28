'use strict';
App.factory('contactService',
		[ '$http', '$q','$filter', function($http, $q,$filter) {
			
			var REST_SERVICE_URILM1 = 'http://localhost:8089/contact/';	
			var factory={
					drop_message:drop_message,
			};
			
		    return factory;
		    
			function drop_message(name,email,message)
			{ 
				var deferred = $q.defer();
				var s=name+","+email+","+message;
				console.log(s);
				 $http.post(REST_SERVICE_URILM1+s)
				 .then(
				 function (response) {
				 deferred.resolve(response.data);
				// window.location="car1.html";
				                
				 },
				 function(errResponse){
				// window.location="SignUp.html";
				 console.error('Error while creating User');
				 deferred.reject(errResponse);
				                
				 }
				 );
				 return deferred.promise;
				
			}
			

			
		}]);