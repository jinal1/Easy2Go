App1.factory('uploadservice',
		[ '$http', '$q', function($http, $q) {
			
			
			var REST_SERVICE_URIL2 = 'http://localhost:8089/saveimage';
			
			var factory = {				
					uploadimage:uploadimage,

				};
			return factory;
			function uploadimage(file) {
				 var deferred = $q.defer();
				  var fd = new FormData();
			        fd.append('file', file);
			        $http.post(REST_SERVICE_URIL2, fd, {
			            //transformRequest: angular.identity,
			            headers: {'Content-Type': undefined}
			        })
			        .success(function(){
			        	
			        })
			        .error(function(){
			        	
			        });

			        return deferred.promise;
			}
			
			
			
			
		}]);