App.controller('contact_controller', ['$scope','contactService', function($scope, contactService) {
	
	var self = this;
	 self.drop_message=drop_message;
	 
	 $scope.name;
	 $scope.email;
	 $scope. message;
	 function drop_message(){
	    	var name=$scope.name;
	    	var email =$scope.email;
	    	var message=$scope.message;
	    	contactService.drop_message(name,email,message)
	    	.then(
	                function(d)
	                {
	                	
//	                	var cardetails={};
//	                    self.cardetailswithimage = d.cardetailwithimage;
//	                    $scope.totalItems = self.cardetailswithimage.length;
//	                  console.log(self.cardetailswithimage.length);
//	                   //console.log(self.cardetailswithimage);
//	              
	           
	                
	                },
	                function(errResponse){
	                    console.error('Error while fetching Contactdetails');
	                }
	              
	            );
	    
	    }
	
}]);