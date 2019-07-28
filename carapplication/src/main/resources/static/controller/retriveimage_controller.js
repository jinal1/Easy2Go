
app1.controller('Retrivecontroller', ['$scope', '$http', function($scope,$http){
	
	$scope.imagename="i3.png";
	$scope.getImages=function(imageName)
	{	
		//alert("Inside"+imageName);
		var imageUrl; 
		$http.get('/getImages/'+imageName, {responseType: 'arraybuffer'})
		 .success(function (data) {
			 var arrayBufferView = new Uint8Array(data);
                var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                var urlCreator = window.URL || window.webkitURL;
                imageUrl = urlCreator.createObjectURL(blob);
             //  $scope.img_url =imageUrl;
		  })
		  .error(function(data, status, headers, config) {
				//$scope.getAllAmc();
			  
//				alert( "Exception details: " + JSON.stringify({data: data}));
			});
		alert("got the image:"+imageUrl);
		 return imageUrl;
	};

	
	
	
	
	
	
}]);