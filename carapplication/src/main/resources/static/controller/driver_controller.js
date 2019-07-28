'use strict';
//var h=angular.module('myApp',[]);
App.controller('DriverController',['$scope','$timeout','DriverService', function($scope,$timeout,DriverService) {
    var self = this;
    self.driver={drivername:'',price:'',experience:'',license:'',vehicalType:'',startDate:'', endDate:'',isActive:'false'};
    self.drivers=[];
    $scope.successMessage="hello";
    $scope.date = new Date();
    $scope.successMessagebool = false;
    self.submitdriver = submitdriver;
    self.bookme=bookme;
    self.fetchAllDrivers=fetchAllDrivers;
    self.fetchDurationDrivers=fetchDurationDrivers;
    function bookme(driverid,driverprice)
    {
      	var url=new URL(window.location.href);
      	console.log(driverid);
      	console.log(driverprice);
       	var id=url.searchParams.get("id");
       	var startdate=url.searchParams.get("startdate");
    	var enddate=url.searchParams.get("enddate");
    	var price=url.searchParams.get("price");
    	   DriverService.bookme(id,startdate,enddate,price,driverid,driverprice)
    	   .then(
    	    		
    		   		 function(res) {
    		   			 console.log(res);
    		   		  var carid=res.car1id;
    		   		   var did=res.driverid;
    		   		   var totalprice=res.totalprice;
    		   		   var priceofcar=res.priceofcar;
    		   		   var priceofdriver=res.priceofdriver;
    		   		window.location="account_cardriver.html?id="+carid+"&driverid="+did+"&price="+totalprice+"&priceofcar="+priceofcar+"&priceofdriver="+priceofdriver;
    		   			
    		   			 
    		            },
    		   		 //console.log('create User method', self.user);		
    		            function(errResponse)
    		            {
    		       console.error('Error while creating User');
    		   }
    		);
    	
    }

	  function fetchDurationDrivers()
 {

 	var url=new URL(window.location.href);
 	var id=url.searchParams.get("id");
 	var startdate=url.searchParams.get("startdate");
 	var enddate=url.searchParams.get("enddate");
 	var price=url.searchParams.get("price");
 	
  DriverService.fetchDurationDrivers(startdate,enddate,price)
  .then(
  		
 		 function(res) {
 			 console.log(res);
 		   self.drivers=res;
 			
 			 
          },
 		 //console.log('create User method', self.user);		
          function(errResponse)
          {
     console.error('Error while creating User');
 }
);
  
 }
  
  
    function submitdriver() {
        if(self.driver.experience!=null){
            console.log('Saving New Driver', self.driver);
           // DriverService.createDriver(self.driver);
            DriverService.uploadPic(self.driver.license,self.driver)
             DriverService.createDriver(self.driver)
            
           .then(
            		
            		 function(res) {
            			 console.log(self.driver.price);
            			  
                        	//window.location="Accountopd.html";
            			 $scope.successMessage = "driver Detail Submitted Succesfully and click on give account details";
                         $scope.successMessagebool = true;
                         $timeout(function () {
                             $scope.successMessagebool = false;
                            
                         }, 10000);
            			 
                     },
            		 		
                     function(errResponse)
                     {
                    	 console.log(self.driver.price);
                console.error('Error while creating User');
           	 $scope.successMessage = "driver Detail Submitted Succesfully and click on give account details";
             $scope.successMessagebool = true;
             $timeout(function () {
                 $scope.successMessagebool = false;
                
             }, 10000);
             	//window.location="Accountopd.html";
            }
        );
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
    }

    function fetchAllDrivers()
    {
    	//  console.log('getting user from back end');
    	DriverService.fetchAllDrivers()
            .then(
            function(d) {
                self.drivers = d;
               // alert( self.users);
               // console.log(self.users);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

}]);
