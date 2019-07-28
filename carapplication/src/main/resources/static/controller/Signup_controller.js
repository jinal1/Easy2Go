'use strict';
//var h=angular.module('myApp',[]);
App.controller('Signupcontroller', ['$scope', '$timeout','SignupService', function($scope,$timeout, SignupService) {
    var self = this;
    self.user={username:'',emailid:'',mobilenumber:'',password:'',confirmpassword:'',dob:'',city:'',address:''};
    self.users=[];
    self.userroles={role:''};
    var name;
    $scope.successMessage="hello";
    $scope.successMessagebool = false;
    self.submituser = submituser;
   self.fetchAllUsers=fetchAllUsers;
   self.updateuser=updateuser;
    self.fetchCurrentUser=fetchCurrentUser;
    $scope.rate = 7;
    $scope.max = 10;
    $scope.isReadonly = false;

    $scope.hoveringOver = function(value) {
      $scope.overStar = value;
      $scope.percent = 100 * (value / $scope.max);
    };

    $scope.ratingStates = [
      {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
      {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
      {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
      {stateOn: 'glyphicon-heart'},
      {stateOff: 'glyphicon-off'}
    ];
    
    function updateuser(){
    	// console.log('getting user from back end');
    	SignupService.updateuser(self.user)
            .then(
            function(d) {
            	console.log("in controller");
                
            	 $scope.successMessage = "Form submitted successfully";
                 $scope.successMessagebool = true;
                 $timeout(function () {
                     $scope.successMessagebool = false;
                    
                 }, 5000);
              
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    function fetchCurrentUser(){
    	//  console.log('getting user from back end');
    	SignupService.fetchCurrentUser()
            .then(
            function(d) {
                self.user = d;
                
               
               console.log(self.user);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    function fetchAllUsers(){
    	//  console.log('getting user from back end');
    	SignupService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
               // alert( self.users);
               // console.log(self.users);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
    function submituser() {
        if(self.user.username!=null){
            console.log('Saving New User',self.user);
            SignupService.createUser(self.user)
            .then(
            		 //console.log('create User method', self.user);		
                     function(d)
                     {
                    	 console.log(d);
                     $scope.successMessage = "Form submitted successfully";
                     $scope.successMessagebool = true;
                     $timeout(function () {
                         $scope.successMessagebool = false;
                        
                     }, 5000);
                console.error('Error while creating User');
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
    }

}]);
