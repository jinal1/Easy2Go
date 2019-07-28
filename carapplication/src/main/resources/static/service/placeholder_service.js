'use strict';

App.factory('PlaceHolderService', ['$http', '$q','$filter', function($http, $q,$filter){

    var REST_SERVICE_URI = 'http://localhost:8089/savePlaceholder';
    var REST_SERVICE_URIL='http://localhost:8089/findallplaceholders';
    var REST_SERVICE_URIL1='http://localhost:8089/getfilterparking/';
    var placeholder={};
    var factory =
    {
        
        createPlaceHolder: createPlaceHolder,
        fetchAllPlaceholders:fetchAllPlaceholders,  
        uploadPic:uploadPic,
        upload:upload,
        uploadnew:uploadnew,
        getfilterparking:getfilterparking,
        
    };

    
    return factory;
    
    function getfilterparking(address,parkingLocation,date,startTime,endTime)
	{
    	console.log("in placeholder js file");
    	var deferred = $q.defer();
    	
		var s=address+","+parkingLocation+","+date+","+startTime+","+endTime;
		console.log(s);
		 $http.post(REST_SERVICE_URIL1+s)
		 .then(
		 function (response) {
			 console.log("in placeholder response");
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
	
    
    function uploadPic(file,file1,a) {
      	 // $state.go('car3new');
         // $scope.formUpload = true;
          if (file != null)
          {
            upload(file,false,a);
            uploadnew(file1,false,a);
          }
        };
function uploadnew(file,resumable,a)
{
	var formData=new FormData();
  formData.append("file",file);
	  $http({
		  method: 'POST',
		  url: 'http://localhost:8089/saveimage2',
		  data: formData,
		  headers: {'Content-Type': undefined }
		}).then(function successCallback(response) {
		
			
			
			
			
		  }, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		  });
	 
	}
        function  upload(file,resumable,a) {
        /*  $scope.errorMsg = null;
          if ($scope.howToSend === 1) {
            uploadUsingUpload(file, resumable);
          } else if ($scope.howToSend == 2) {
            uploadUsing$http(file);
          } else {
            uploadS3(file);
          }*/
      placeholder.placeHolderName=a.placeHolderName;
      placeholder.address=a.address;
      placeholder.squarefeet=a.squarefeet;
      placeholder.parkingLocation=a.parkingLocation;
      placeholder.startTime=a.startTime;
      placeholder.endTime=a.endTime;
      placeholder.parkingPhoto="pics/"+a.parkingPhoto.name;
      placeholder.idProof="pics/"+a.idProof.name;
      placeholder.price=a.price;
      placeholder.inportal ='true';
      placeholder.isActive='false'; 
     var d=placeholder.startTime;
      var e=placeholder.endTime;
     // placeholder.startTime=$filter('date')(d,"yyyy-MM-dd HH:mm:ss");
      //placeholder.endTime=$filter('date')(e,"yyyy-MM-dd HH:mm:ss");
      console.log(placeholder);
       	 createPlaceHolder(placeholder);
       	
      	  var formData=new FormData();
      	  formData.append("file",file);
      	  $http({
      		  method: 'POST',
      		  url: 'http://localhost:8089/saveimage2',
      		  data: formData,
      		  headers: {'Content-Type': undefined }
      		}).then(function successCallback(response) {
      		
      			
      			
      			
      			
      		  }, function errorCallback(response) {
      		    // called asynchronously if an error occurs
      		    // or server returns response with an error status.
      		  });
      	 
      	  
        }

 

    function createPlaceHolder(placeholder) {
        var deferred = $q.defer();
       
        $http.post(REST_SERVICE_URI,placeholder)
            .then(
            function (response) {
                deferred.resolve(response.data);
                
              // window.location="index.html";
                
            },
            function(errResponse){
            	// window.location="index.html";
                console.error('Error while creating User');
                deferred.reject(errResponse);
                
            }
        );
        return deferred.promise;
    }

    function fetchAllPlaceholders()
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
