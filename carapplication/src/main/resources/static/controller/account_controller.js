'use strict';
//var h=angular.module('myApp',[]);
App.controller('accountcontroller', ['$scope', '$timeout','accountService', function($scope,$timeout, accountService) {
    var self = this;
    self.account={accountno:'',balance:''};
    self.CustomerParkingBooking={location:'',pickupDate:'',pickupTime:'',dropoffTime:'',totalprice:''}
    self.accounts=[];
    var placeholderid1;
    var totalprice1;
    $scope.bankname;
   
    $scope.securityCode;
    $scope.carname;
    $scope.successMessage="hello";
    $scope.successMessagebool = false;
    self.submit=submit;
    self.submitopd=submitopd;
   self.payment=payment;
   self.submit1=submit1;
   self.doparkingpayment=doparkingpayment;
   self.getparkingreceipt=getparkingreceipt;
   self.payment1=payment1;
   self.downloadreceipt=downloadreceipt;
   self.attachtomail=attachtomail;
   self.downloadreceipt1=downloadreceipt1;
   self.attachtomail1=attachtomail1;
   self.getcardriverbookingreceiptvalues=getcardriverbookingreceiptvalues;
   self.getcustomerbookingreceiptvalues=getcustomerbookingreceiptvalues;
   self.saveparkingdetail=saveparkingdetail;
   function saveparkingdetail()
   {
	   var url=new URL(window.location.href);
		
	      var price =url.searchParams.get("price");
	      var id=url.searchParams.get("placeHolderId");
	      accountService.saveparkingdetail(price,id)
	      .then(
	                function(d)
	                {
	                	
	                placeholderid1=d.placeholderid;
	                	console.log(placeholderid1);
	                	totalprice1=d.totalprice;
	                	
	                	
	                	//window.location="parkingbookingreceipt.html";
	                	
	                },
	                function(errResponse){
	                    console.error('Error while fetching Cardetails');
	                }
	              
	            );
	      
   }
   
   function doparkingpayment()
   {
	   window.location="parkingbookingreceipt.html?id="+placeholderid1+"&totalprice="+totalprice1;
	  
	      
   }
   function getparkingreceipt()
   { var url=new URL(window.location.href);
	
  var a=url.searchParams.get("id");
   var b=url.searchParams.get("totalprice");
   accountService.getparkingreceipt(a,b) 
   .then(
           function(d)
           {
           	
           	self.CustomerParkingBooking.pickupDate=d.date;
           	console.log(self.3CustomerParkingBooking);
         	self.CustomerParkingBooking.pickupTime=d.starttime;
         	self.CustomerParkingBooking.dropoffTime=d.endtime;
         	self.CustomerParkingBooking.totalprice=d.totalprice;
         	self.CustomerParkingBooking.location=d.location;
           	$scope.carname=d.carname;
           	console.log($scope.carname)
           	
           },
           function(errResponse){
               console.error('Error while fetching Cardetails');
           }
         
       );
	   
   }
   function downloadreceipt()
   {
	   var url=new URL(window.location.href);
		
	      $scope.price =url.searchParams.get("totalprice");
	      $scope.name =url.searchParams.get("name");
	      $scope.companyname =url.searchParams.get("companyname");
	      $scope.pickupdate=url.searchParams.get("pickupdate");
	     $scope.dropofdate=url.searchParams.get("dropofdate");
	     var id=url.searchParams.get("id");
	    	accountService.downloadreceipt($scope.name, $scope.price,  $scope.companyname, $scope.pickupdate, $scope.dropofdate)
	    	.then(
	                function(d)
	                {
	                	
	                	
	                	
	                	
	                	
	                	
	                },
	                function(errResponse){
	                    console.error('Error while fetching Cardetails');
	                }
	              
	            );
	   
   }
   function attachtomail()
   {
	   
   }
   function downloadreceipt1()
   {
	   
   }
   function attachtomail1()
   {
	   
   }
   function payment(){
   	var url=new URL(window.location.href);
       var a=url.searchParams.get("price");
       var id=url.searchParams.get("id");
   	accountService.payment(a,id,$scope.securityCode)
   	.then(
               function(d)
               {
               	var a=d.name;
            	var b=d.totalprice;
            	var c=d.cname;
            	var d1=d.pdate;
            	var e=d.ddate;
            	window.location="customer_bookingdetail.html?name="+a+"&totalprice="+b+"&companyname="+c+"&pickupdate="+d1+"&dropofdate="+e;
               	
               },
               function(errResponse){
                   console.error('Error while fetching Cardetails');
               }
             
           );
   	
   }
   function getcustomerbookingreceiptvalues()
   {
		var url=new URL(window.location.href);
			
	      $scope.price =url.searchParams.get("totalprice");
	      $scope.name =url.searchParams.get("name");
	      $scope.companyname =url.searchParams.get("companyname");
	      $scope.pickupdate=url.searchParams.get("pickupdate");
	     $scope.dropofdate=url.searchParams.get("dropofdate");
	     
   }
    function submit(){
    	var url=new URL(window.location.href);
        var a=url.searchParams.get("price");
        var b=url.searchParams.get("id");
    	accountService.submit(self.account,$scope.bankname,a,b)
    	.then(
                function(d)
                {
                	
                	
               var ra=d.random;
               
               var pr= d.price;
             var id=d.idofcar;
             
       		window.location="securitycode.html?id="+id+"&price="+pr;
       	
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    	
    }
    function submitopd(){
    	
    	accountService.submitopd(self.account,$scope.bankname)
    	.then(
                function(d)
                {
                	
                	 $scope.successMessage = "account detail submitted successfully";
                     $scope.successMessagebool = true;
                     $timeout(function () {
                         $scope.successMessagebool = false;
                        
                     }, 5000);
       	
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                    $scope.successMessage = "account detail submitted successfully";
                    $scope.successMessagebool = true;
                    $timeout(function () {
                        $scope.successMessagebool = false;
                       
                    }, 5000);
                }
              
            );
    	
    }
    
    function submit1(){
    	var url=new URL(window.location.href);
        var a=url.searchParams.get("price");
        var b=url.searchParams.get("id");
        var c=url.searchParams.get("driverid");
        var d=url.searchParams.get("priceofcar");
        	var e=url.searchParams.get("priceofdriver");
    	accountService.submit1(self.account,$scope.bankname,a,b,c,d,e)
    	.then(
                function(d)
                {
                	
                	
               var ra=d.random;
               
               var pr= d.price;
             var id=d.idofcar;
             var driverid=d.driverid;
             var pc=d.priceofcar;
             var pd=d.priceofdriver;
             
       		window.location="security_cardriver.html?id="+id+"&price="+pr+"&driverid="+driverid+"&pc="+pc+"&pd="+pd;
       	
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    	
    }
     
    function payment1(){
       	var url=new URL(window.location.href);
           var a=url.searchParams.get("price");
           var id=url.searchParams.get("id");
           var driverid=url.searchParams.get("driverid");
           var pc=url.searchParams.get("pc");
           var pd=url.searchParams.get("pd");
       	accountService.payment1(a,id,driverid,$scope.securityCode,pc,pd)
       	.then(
                   function(d)
                   {
                   	var a=d.name;
                	var b=d.priceofcar;
                	var c=d.cname;
                	var d1=d.pdate;
                	var e=d.ddate;
                	var f=d.drivername;
                	var g=d.priceofdriver;
                	var h=d.totalprice;
                	window.location="cardriver_bookingreceipt.html?name="+a+"&priceofcar="+b+"&companyname="+c+"&pickupdate="+d1+"&dropofdate="+e+"&drname="+f+"&priceofdriver="+g+"&totalprice="+h;
                   	
                   },
                   function(errResponse){
                       console.error('Error while fetching Cardetails');
                   }
                 
               );
       	
       }
    function getcardriverbookingreceiptvalues()
    {

		var url=new URL(window.location.href);
			
	      $scope.totalprice =url.searchParams.get("totalprice");
	      $scope.name =url.searchParams.get("name");
	      $scope.companyname =url.searchParams.get("companyname");
	      $scope.pickupdate=url.searchParams.get("pickupdate");
	     $scope.dropofdate=url.searchParams.get("dropofdate");
	     $scope.dribvername=url.searchParams.get("drname");
	     $scope.priceofcar=url.searchParams.get("priceofcar");
	    	 $scope.priceofdriver=url.searchParams.get("priceofdriver");
	    	 
	    	 
    }

}]);
