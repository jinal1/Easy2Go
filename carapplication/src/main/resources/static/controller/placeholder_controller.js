'use strict';
//var h=angular.module('myApp',[]);
App.controller('PlaceHolderController', ['$scope','$timeout','$filter','PlaceHolderService', function($scope,$timeout,$filter,PlaceHolderService) {
    var self = this;
    self.placeholder={placeHolderId:'null',placeHolderName:'',address:'',squarefeet:'',isActive:'false',parkingLocation:'',parkingPhoto:'',idProof:'',startTime:'',endTime:'',price:''};
    self.placeholders=[];
    $scope.successMessage="hello";
    $scope.successMessagebool = false;
    $scope.currentPage = 1;
    $scope.itemsPerPage = $scope.viewby;
    $scope.maxSize = 5; 
    $scope.address;
    $scope.parkingLocation;
    $scope.startTime;
    $scope.endTime;
    self.submitplaceholder = submitplaceholder;
    self.getfilterparking=getfilterparking;
   self.fetchAllPlaceholders=fetchAllPlaceholders;
    function submitplaceholder() {
        if(self.placeholder.placeHolderName!=null){
            console.log('Saving New PlaceHolder', self.placeholder);
          
            
            PlaceHolderService.uploadPic(self.placeholder.parkingPhoto,self.placeholder.idProof,self.placeholder)
            PlaceHolderService.createPlaceHolder(self.placeholder)
            .then(
            		function(res)
            		{
            			 console.log(self.placeholder);
            				//window.location="Accountopd.html";
            			 $scope.successMessage = "Placeholder Detail Submitted Succesfully";
                         $scope.successMessagebool = true;
                         $timeout(function () {
                             $scope.successMessagebool = false;
                            
                         }, 10000);
            			 //PlaceHolderService.createPlaceHolder(self.placeholder);
            		},
            		 //console.log('create User method', self.user);		
                     function(errResponse)
                     {
            			 $scope.successMessage = "Placeholder Detail Submitted Succesfully";
                         $scope.successMessagebool = true;
                         $timeout(function () {
                             $scope.successMessagebool = false;
                            
                         }, 10000);
                    
                console.error('Error while creating User');
             	//window.location="Accountopd.html";
            }
        );
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
    }

    function getfilterparking(){
    	var address=$scope.address;
    	var parkingLocation=$scope.parkingLocation;
    	var startTime=$scope.startTime;
    	var endTime=$scope.endTime;
   	 var date=$filter('date')(new Date(startTime),"yyyy-MM-dd");
		 var d1=$filter('date')(new Date(startTime),"hh:mm:ss");
		 var e1=$filter('date')(new Date(endTime),"hh:mm:ss");
    	PlaceHolderService.getfilterparking(address,parkingLocation,date,d1,e1)
    	.then(
                function(d)
                {
                	
                	self.placeholders=d;
                
                   //console.log(self.cardetailswithimage);
              
           
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    
    }
    
    function fetchAllPlaceholders()
    {
    	//  console.log('getting user from back end');
    	PlaceHolderService.fetchAllPlaceholders()
            .then(
            function(d) {
                self.placeholders = d;
           
               // alert( self.users);
               // console.log(self.users);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    
    $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
      };

      $scope.pageChanged = function() {
        console.log('Page changed to: ' + $scope.currentPage);
      };
    
    $scope.setItemsPerPage = function(num) {
      $scope.itemsPerPage = num;
      $scope.currentPage = 1; //reset to first page
    }
    
    
}]);
