'use strict';
//var h=angular.module('myApp',[]);
app1.controller('GarageController', ['$scope', 'GarageService', function($scope, GarageService) {
    var self = this;
    self.garageowner={ownerName:'',locationOfgarage:'',serviceprovided:'',timingOfGarage:''};
    self.garageowners=[];

    self.submitgarage = submitgarage;
   self.fetchAllGarages=fetchAllGarages;
    function submitgarage() {
        if(self.garageowner.ownerName!=null)
        {
            console.log('Saving New User', self.garageowner);
            GarageService.createGarage(self.garageowner)
            .then(
            		 //console.log('create User method', self.user);
            		
            		
            		 $scope.successMessage = "Form submitted successfully";
                     $scope.successMessagebool = true;
                     $timeout(function () {
                         $scope.successMessagebool = false;
                        
                     }, 5000);
                     function(errResponse)
                     {
                console.error('Error while creating User');
            }
        );
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
    }
    function fetchAllGarages()
    {
    	//  console.log('getting user from back end');
    	GarageService.fetchAllGarages()
            .then(
            function(d) {
                self.garageowners = d;
               // alert( self.users);
               // console.log(self.users);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

}]);
