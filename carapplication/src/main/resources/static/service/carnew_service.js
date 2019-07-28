'use strict';
angular.module('myApp').factory('carnewService',
		[ '$http', '$q','$filter', function($http, $q,$filter) {

			var REST_SERVICE_URI = 'http://localhost:8089/saveCar1';
			var REST_SERVICE_URIL = 'http://localhost:8089/saveLicense';
			var REST_SERVICE_URIL1 = 'http://localhost:8089/saveBooking';
			var REST_SERVICE_URIL2 = 'http://localhost:8089/saveimage';
			var REST_SERVICE_URIL3='http://localhost:8089/savefeature';
			var REST_SERVICE_URILJ='http://localhost:8089/getcars';
			var REST_SERVICE_URILJ1='http://localhost:8089/getonecar/';
			var REST_SERVICE_URILJ2='http://localhost:8089/updateonecar/';
			var REST_SERVICE_URILJ3='http://localhost:8089/getbookingdetails/';
			var REST_SERVICE_URILJ4='http://localhost:8089/getbookdeatailsforreceipt/';
			var REST_SERVICE_URILJ5='http://localhost:8089/comparecars/';
			var REST_SERVICE_URILJ6='http://localhost:8089/getcarswithauthentication/';
			var REST_SERVICE_URILJ7='http://localhost:8089/getcarhistoryforcurrentuser/';
			var REST_SERVICE_URILJ8='http://localhost:8089/getsearchcar/';
			var REST_SERVICE_URILJ9='http://localhost:8089/getfilter/';
			var REST_SERVICE_URILJ10='http://localhost:8089/setcustomerbooking/';
			var REST_SERVICE_URILJ11='http://localhost:8089/getreturncarrequest/';
			var REST_SERVICE_URILJ12='http://localhost:8089/updatecarinportal/';
			var cardetails = {};
			var Licensedetails={};
			var Bookingdetails={};
			var carfeature={};
			var id;
			var car={};
			var carbooking={};
			var customercarbooking={};
			var factory = {
					historyvehicle:historyvehicle,
				createCar : createCar,
				setCarData : setCarData,
				getCarData : getCarData,
				setLicenseData:setLicenseData,
				getLicenseData:getLicenseData,
				createLicense:createLicense,
				setBookingData:setBookingData,
				getBookingData:getBookingData,
				createBooking:createBooking,
				createFeature:createFeature,
				getcardetails:getcardetails,
				getreturncardetails:getreturncardetails,
				getauthcardetails:getauthcardetails,
				golisting:golisting,
				updateAndNotification:updateAndNotification,
				search:search,
				getbookingdata:getbookingdata,
				setcustomerbooking:setcustomerbooking,
				comparecar:comparecar,
				getsearchcar:getsearchcar,
				getfilter:getfilter,
				upload:upload,
				uploadPic:uploadPic,
				updatecarinportal:updatecarinportal,


			};

			return factory;
			function getfilter(location,brand,year,model,price)
			{ var deferred = $q.defer();
				var s=location+","+brand+","+year+","+model+","+price;
				console.log(s);
				 $http.get(REST_SERVICE_URILJ9+s)
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
			
			function getsearchcar(a,b,c,d,e,f,g)
			{
				 var d1=$filter('date')(new Date(d.split('/').join('-')),"yyyy-MM-dd");
				 var e1=$filter('date')(new Date(e.split('/').join('-')),"yyyy-MM-dd");
				 carbooking.carduration=a;
				 carbooking.pickuplocation=b;
				 carbooking.dropofflocation=c;
				 carbooking.pickupdate=d1;
				 carbooking.dropoffdate=e1;
				 carbooking.pickuptime=f;
				 carbooking.dropofftime=g;
				 
				 carbooking.bookingId=0;
				 console.log(carbooking);
				 var deferred = $q.defer();
				 $http.post(REST_SERVICE_URILJ8,carbooking)
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
			function historyvehicle()
			{
				 var deferred = $q.defer();
				 $http.get(REST_SERVICE_URILJ7)
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
	function setcustomerbooking(customercarbooking,c)
	{
		 var deferred = $q.defer();
		 uploadPic(customercarbooking.customerLicense);
		 console.log(customercarbooking);
		 console.log(c);
		 customercarbooking.customerLicense="pics/"+ customercarbooking.customerLicense.name;
		 console.log(customercarbooking);
		 $http.post(REST_SERVICE_URILJ10+c,customercarbooking)
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
	
	
	function uploadPic(file) {
	   	 // $state.go('car3new');
	      // $scope.formUpload = true;
	       if (file != null) {
	         upload(file,false);
	       }
	     };

	     function  upload(file, resumable) {
	     /*  $scope.errorMsg = null;
	       if ($scope.howToSend === 1) {
	         uploadUsingUpload(file, resumable);
	       } else if ($scope.howToSend == 2) {
	         uploadUsing$http(file);
	       } else {
	         uploadS3(file);
	       }*/
	    	/* driver.drivername=a.drivername;
	    	 driver.startDate=a.startDate;
	    	 driver.endDate=a.endDate;
	    	 driver.vehicalType=a.vehicalType;
	    	 driver.experience=a.experience;
	    	
	    	 createDriver(driver);
	    	 console.log(driver);*/
	   	  var formData=new FormData();
	   	  formData.append("file",file);
	   	  $http({
	   		  method: 'POST',
	   		  url: 'http://localhost:8089/saveimage3',
	   		  data: formData,
	   		  headers: {'Content-Type': undefined }
	   		}).then(function successCallback(response) {
	   		
	   		  }, function errorCallback(response) {
	   		    // called asynchronously if an error occurs
	   		    // or server returns response with an error status.
	   		  });
	   	 
	   	  
	     };
			function getCarData() {
				return cardetails;
			}
			function setCarData(data) {
				cardetails = data;
			}
			function setLicenseData(data) {
				Licensedetails = data;
			}
			function getLicenseData() {
				return Licensedetails;
			}
			function setBookingData(data) {
				Bookingdetails = data;
			}
			function getBookingData() {
				return Bookingdetails;
			}
			function createBooking(carbooking) {
				 var deferred = $q.defer();
				
				 
		
				 $http.post(REST_SERVICE_URIL1,carbooking)
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
			function createLicense(license) {
				 var deferred = $q.defer();
				 $http.post(REST_SERVICE_URIL,license)
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
			function createCar(car) {
				 var deferred = $q.defer();
				 $http.post(REST_SERVICE_URI,car)
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
			function createFeature(carfeature) {
				 var deferred = $q.defer();
				 $http.post(REST_SERVICE_URIL3,carfeature)
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
		
			
			function getcardetails()
			{
			      var deferred = $q.defer();
			        $http.get(REST_SERVICE_URILJ)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			               console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;
			}
			function getauthcardetails()
			{
				 var deferred = $q.defer();
			        $http.get(REST_SERVICE_URILJ6)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			               console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;	
			}
			function getreturncardetails()
			{
				 var deferred = $q.defer();
			        $http.get(REST_SERVICE_URILJ11)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			               console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;	
			}
			function search(carbooking)
			{
				  var deferred = $q.defer();
			        $http.get(REST_SERVICE_URILJ3,carbooking)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			               console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;
				
			}
			function comparecar(name1,name2,name3)
			{
				var array=[name1,name2,name3]
				 var deferred = $q.defer();
				 console.log(REST_SERVICE_URILJ5+array);
			        $http.get(REST_SERVICE_URILJ5+name1+","+name2+","+name3)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			                console.log("hellooooooooo");
			                console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;
			}
			function golisting(id)
			{
				 var deferred = $q.defer();
			        $http.get(REST_SERVICE_URILJ1+id)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			                console.log("hellooooooooo");
			                console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;
			}
			function updatecarinportal(id)
			{
				 var deferred = $q.defer();
			        $http.put(REST_SERVICE_URILJ12+id)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			                console.log("hellooooooooo");
			                console.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;
			}
			function getbookingdata(id)
			{
				 var deferred = $q.defer();
			        $http.get(REST_SERVICE_URILJ4+id)
			            .then(
			            function (response) {
			                deferred.resolve(response.data);
			               // console.log("hellooooooooo");
			                //onsole.log(response.data);
			            },
			            function(errResponse){
			                console.error('Error while fetching Cardetails');
			                deferred.reject(errResponse);
			            }
			        );
			        return deferred.promise;
				
			}
function updateAndNotification(car,id)
{
	 var deferred = $q.defer();
	 console.log(car);
     $http.put(REST_SERVICE_URILJ2+id,car)
         .then(
         function (response) {
             deferred.resolve(response.data);
             console.log("hellooooooooo");
             console.log(response.data);
         },
         function(errResponse){
             console.error('Error while fetching Cardetails');
             deferred.reject(errResponse);
         }
     );
     return deferred.promise;
	}
		} ]);
