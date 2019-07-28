'use strict';

App.factory('accountService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8089/saveaccountdetails/';
    var REST_SERVICE_URIJ = 'http://localhost:8089/makepayment/';
    var REST_SERVICE_URIJ1 = 'http://localhost:8089/makepayment1/';
    var REST_SERVICE_URI1 = 'http://localhost:8089/saveaccountdetails1/';
    var REST_SERVICE_URIL='http://localhost:8089/convertpdf/';
    var REST_SERVICE_URILJ2 = 'http://localhost:8089/saveparkingdetail/';
    var REST_SERVICE_URIJi='http://localhost:8089/makepayment2/';
    var REST_SERVICE_URI3 = 'http://localhost:8089/saveaccountdetailsopd/';
var account={};

    var factory = {
    		submit:submit,
    		payment:payment,
    		submit1:submit1,
    		submitopd:submitopd,
    		downloadreceipt:downloadreceipt,
    		payment1:payment1,
    		saveparkingdetail:saveparkingdetail,
    		getparkingreceipt:getparkingreceipt,
    };
    

    return factory;
    function saveparkingdetail(a,b)
    {
    	var deferred = $q.defer();
	    
        var s=a+","+b;
        $http.post(REST_SERVICE_URILJ2+s)
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
    function downloadreceipt(a,b,c,d,e)
    {
    	 var deferred = $q.defer();
    	    
         var s=a+","+b+","+c+","+d+","+e;
         $http.post(REST_SERVICE_URIL+s,account)
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
    function submit(account,bankname,a,b) {
        var deferred = $q.defer();
    
        var s=bankname+","+a+","+b;
        $http.post(REST_SERVICE_URI+s,account)
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
    
    function submitopd(account,bankname) {
        var deferred = $q.defer();
    
        var s=bankname;
        $http.post(REST_SERVICE_URI3+s,account)
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
    
    
    function submit1(account,bankname,a,b,c,d,e) {
        var deferred = $q.defer();
    
        var s=bankname+","+a+","+b+","+c+","+d+","+e;
        $http.post(REST_SERVICE_URI1+s,account)
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
    function payment(a,id,sc) {
        var deferred = $q.defer();
    
        var s=a+","+id+","+sc;
        $http.post(REST_SERVICE_URIJ+s)
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

    function payment1(a,id,driverid,sc,pc,pd) {
        var deferred = $q.defer();
    
        var s=a+","+id+","+driverid+","+sc+","+pc+","+pd;
        $http.post(REST_SERVICE_URIJ1+s)
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
    
    function getparkingreceipt(a,b)
    {
    	 var deferred = $q.defer();
    	    
         var s=a+","+b;
         $http.post(REST_SERVICE_URIJi+s)
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
