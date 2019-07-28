App.controller('uploadcontroller', ['$scope','$state', 'uploadservice', function($scope,$state,uploadservice){
	
	
	var self=this;
	self.submit=submit();
    function submit()
    {
    	 var file=$scope.image;
         uploadservice.uploadimage(file);
    }
	
	
	
	
	
	
	
}]);

