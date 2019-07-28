'use strict';

App.factory('DriverService', ['$http', '$q','$filter', function($http, $q, $filter){

    var REST_SERVICE_URI = 'http://localhost:8089/saveDriver';
    var REST_SERVICE_URIL='http://localhost:8089/driver';
    var REST_SERVICE_URIL1='http://localhost:8089/durationdriver/';
    var REST_SERVICE_URIL2='http://localhost:8089/calculatedriverprice/';
var driver={};
    var factory = {
        
        createDriver: createDriver,
        fetchAllDrivers:fetchAllDrivers,
        uploadPic:uploadPic,
        bookme:bookme,
        fetchDurationDrivers:fetchDurationDrivers,
    upload:upload
        
    };

    return factory;
    function bookme(id,startdate,enddate,price,driverid,driverprice)
    {
    	 var deferred = $q.defer();
    	console.log(id+startdate+enddate+price+driverid+driverprice);
    	var s=id+","+startdate+","+enddate+","+price+","+driverid+","+driverprice;
    	$http.post(REST_SERVICE_URIL2+s)
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
    function fetchDurationDrivers(startdate,enddate,price)
    {
        var deferred = $q.defer();
       
        
        var s=startdate+","+enddate+","+price;
        console.log("hello"+s);
    	 $http.post(REST_SERVICE_URIL1+s)
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
    
    function uploadPic(file,a) {
   	 // $state.go('car3new');
      // $scope.formUpload = true;
       if (file != null) {
         upload(file,false,a);
       }
     };

     function  upload(file, resumable,a) {
     /*  $scope.errorMsg = null;
       if ($scope.howToSend === 1) {
         uploadUsingUpload(file, resumable);
       } else if ($scope.howToSend == 2) {
         uploadUsing$http(file);
       } else {
         uploadS3(file);
       }*/
    	 driver.drivername=a.drivername;
    	 driver.startDate=a.startDate;
    	 driver.endDate=a.endDate;
    	 driver.vehicalType=a.vehicalType;
    	 driver.experience=a.experience;
    	 driver.license="pics/"+a.license.name;
    	 driver.price=a.price;
    	 createDriver(driver);
    	 console.log(driver);
   	  var formData=new FormData();
   	  formData.append("file",file);
   	  $http({
   		  method: 'POST',
   		  url: 'http://localhost:8089/saveimage1',
   		  data: formData,
   		  headers: {'Content-Type': undefined }
   		}).then(function successCallback(response) {
   		console.log("hrllo");
   		
   		  }, function errorCallback(response) {
   		    // called asynchronously if an error occurs
   		    // or server returns response with an error status.
   		  });
   	 
   	  
     };

    function createDriver(driver) {
    	console.log(driver);
        var deferred = $q.defer();
        var d1=driver.startDate;
        var e=driver.endDate;
      
        
        driver.startdate=$filter('date')(d1,"yyyy-MM-dd");
        driver.enddate=$filter('date')(e,"yyyy-MM-dd");
       console.log(driver.price);
        
        $http.post(REST_SERVICE_URI,driver)
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

    function fetchAllDrivers()
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
