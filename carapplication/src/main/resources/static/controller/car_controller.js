App.controller('carcontroller', ['$scope','$window','$location','$state', '$log','carnewService', function($scope,$window,$location,$state,$log, carnewService) {
var self = this;
self.car={car1id:null,isAuthenticated:'false',isActive:'false',inPortal:'true',cityname:'',year:'',companyname:'',model:'',transmission:'',kmdriven:'',vehicletype:'',marketvalue:'',color:'',capacity:'',fueltype:'',carplatenumber:'',cardescription:'',price:'',comanyinfo:'',businessaddress:'',website:'',insuranceprovider:'',policynumber:''};
self.license={licensenumber:'',firstname:'',middlename:'',lastname:'',dateofbirth:'',issuingstate:''};
self.carfeature={id:null,evhybrid:'false',bikerack:'false',allwheeldrive:'false',childseat:'false',snowtires:'false',skirack:'false',bluetooth:'false',gps:'false',usbinput:'false',heatedseats:'false',audioinput:'false',convertible:'false',petfriendly:'false',tollpass:'false',sunroof:'false'};
self.customercarbooking={pickupLocation:'',dropoffLocation:'',pickupTime:'',dropoffTime:'',pickupDate:'',dropoffDate:'',driverRequired:'false',customerLicense:'',totalprice:''}
self.carbooking={carduration:'',pickupdate:'',pickuptime:'',pickuplocation:'', temppickupdate:'',tempdropoffdate:'',dropoffdate:'',dropofftime:'',dropofflocation:''};
self.cars=[];
self.carone={};
self.cardetailswithimage=[];
var url;
$scope.location;
$scope. brand;
$scope. year;
$scope.model;
$scope.price;
$scope.name1;
$scope.name2;
$scope.name3;
$scope.value1 = true;
$scope.value2=false;
var a=$scope.username;
$scope.viewby = 3;
$scope.f1;
$scope.f2;
$scope.f3;
$scope.f4;
$scope.f5;
$scope.f6;
$scope.f7;
$scope.f8;
$scope.f9;
$scope.f10;
$scope.f11;
$scope.f12;
$scope.f13;
$scope.f14;
$scope.f15;
$scope.currentPage = 1;
$scope.itemsPerPage = $scope.viewby;
$scope.maxSize = 5; 
    self.next=next;
    self.submit1=submit1;
    self.submit2=submit2;
    self.submit3=submit3;
    self.submit4=submit4;
    self.submit=submit;
  
    self.golisting=golisting;
    self.getcardetails=getcardetails;
    self.getreturncardetails=getreturncardetails;
    self.getauthcardetails=getauthcardetails;
    self.getbookingdata=getbookingdata;
    self.getImageUrlUsingBlob=getImageUrlUsingBlob;
    self.updateAndNotification=updateAndNotification;
    self.onecar=onecar;
    self.historyvehicle=historyvehicle;
    self.search=search;
    self.comparecar=comparecar;
    self.setcustomerbooking=setcustomerbooking;
    self.getsearchcar=getsearchcar;
    self.getfilter=getfilter;
    self.updatecarinportal=updatecarinportal;
    function getfilter(){
    	var location=$scope.location;
    	var brand=$scope.brand;
    	var model=$scope.model;
    	var year=$scope.year;
    	var price=$scope.price;
    	carnewService.getfilter(location,brand,model,year,price)
    	.then(
                function(d)
                {
                	
                	var cardetails={};
                    self.cardetailswithimage = d.cardetailwithimage;
                    $scope.totalItems = self.cardetailswithimage.length;
                  console.log(self.cardetailswithimage.length);
                   //console.log(self.cardetailswithimage);
              
           
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    
    }
    function getsearchcar()
    {
    	var url=new URL(window.location.href);
    var a=url.searchParams.get("id1");
    	console.log(a);
    	self.carbooking.carduration=a;
    	console.log(self.carbooking.carduration);
    	var b=url.searchParams.get("id2");
    	self.carbooking.pickuplocation=b;
var  c=url.searchParams.get("id3");
	self.carbooking.dropofflocation=c;
    	var d=url.searchParams.get("id4");
    	self.carbooking.pickupdate=d;
    	var e =url.searchParams.get("id5");
    	self.carbooking.dropoffdate=e;
    var f=url.searchParams.get("id6");
	self.carbooking.pickuptime=f;
    	var g=url.searchParams.get("id7");
    	self.carbooking.dropofftime=g;
    	carnewService.getsearchcar(a,b,c,d,e,f,g)	
    	.then(
                function(d)
                {
                
                 	var cardetails={};
                        self.cardetailswithimage = d.cardetailwithimage;
                        $scope.totalItems = self.cardetailswithimage.length;
                      console.log(self.cardetailswithimage.length);
                       //console.log(self.cardetailswithimage);
                       
                       
                       
                       
                      /* cardetailswithimage.forEach(function(element) {
                    	   if(element==1){
                    		   
                    	  
                    	    console.log(element.value);
                    	   }
                    	});*/
                       for(var i=0; i < self.cardetailswithimage.length; i++) {
                           //console.log(self.cardetailswithimage[i].car);
                    	
                       //  self.car=self.cardetailswithimage[i].car;
                        
                           //console.log(self.cardetailswithimage[i].b); 
                           //console.log(self.cars.length);
                           
                       /*   var arrayBufferView = new Uint8Array(self.cardetailswithimage[i].b);
                           var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                           var urlCreator = window.URL || window.webkitURL;
                           imageUrl = urlCreator.createObjectURL(blob);
                           console.log(imageUrl);
                           self.cardetailswithimage[i].url=imageUrl;*/
                          
                       }
                       
                       // alert( self.users);
                        //cardetails.car1id=d.car.car1id;
                        //console.log(cardetails.car1id);
                       console.log(self.cars);
                       console.log(self.cardetailswithimage);
                    
              
           
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    }
    //start date picker
    $scope.today = function() {
        self.carbooking.pickupdate = new Date();
        
      };
      $scope.today();

      $scope.clear1 = function()
      {
    	  self.carbooking.pickupdate= null;
      };

      $scope.inlineOptions = 
      {
        customClass: getDayClass,
        minDate: new Date(),
        showWeeks: true
      };

      $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
      };

      // Disable weekend selection
      function disabled(data) {
        //var date = data.date,
         // mode = data.mode;
        //return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
      }

      $scope.toggleMin = function() {
        $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
        $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
      };

      $scope.toggleMin();

      $scope.open1 = function() {
        $scope.popup1.opened = true;
      };

      $scope.open2 = function() {
        $scope.popup2.opened = true;
      };

      $scope.setDate = function(year, month, day) {
    	  self.carbooking.pickupdate = new Date(year, month, day);
      };

      $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
      $scope.format = $scope.formats[0];
      $scope.altInputFormats = ['M!/d!/yyyy'];

      $scope.popup1 = {
        opened: false
      };

      $scope.popup2 = {
        opened: false
      };

      var tomorrow = new Date();
      tomorrow.setDate(tomorrow.getDate() + 1);
      var afterTomorrow = new Date();
      afterTomorrow.setDate(tomorrow.getDate() + 1);
      $scope.events = [
        {
          date: tomorrow,
          status: 'full'
        },
        {
          date: afterTomorrow,
          status: 'partially'
        }
      ];

      function getDayClass(data) {
        var date = data.date,
          mode = data.mode;
        if (mode === 'day') {
          var dayToCheck = new Date(date).setHours(0,0,0,0);

          for (var i = 0; i < $scope.events.length; i++) {
            var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

            if (dayToCheck === currentDay) {
              return $scope.events[i].status;
            }
          }
        }

        return '';
      }
    //end date picker
    //for time picker
      self.carbooking.pickuptime = new Date();
      self.carbooking.dropofftime = new Date();
      $scope.hstep = 1;
      $scope.mstep = 15;

      $scope.options = {
        hstep: [1, 2, 3],
        mstep: [1, 5, 10, 15, 25, 30]
      };

      $scope.ismeridian = true;
      $scope.toggleMode = function() {
        $scope.ismeridian = ! $scope.ismeridian;
      };

  

      $scope.changed = function () {
        $log.log('Time changed to: ' +     self.carbooking.pickuptime);
      };

      $scope.clear = function() {
    	    self.carbooking.pickuptime = null;
    	    self.carbooking.dropofftime = null;
      };
      //end of time picker
    
    function setcustomerbooking()
    {var url=new URL(window.location.href);
	var c=url.searchParams.get("id");
	console.log(c);
	console.log(self.customercarbooking);
    	carnewService.setcustomerbooking(self.customercarbooking,c)	
    	.then(
                function(d)
                {
                	console.log(d.id);
                	console.log(d.driverre);
                	console.log(d.startdate);
                	console.log(d.enddate);
                	console.log(d.price);
                	var a=d.id;
                	var b=d.driverre;
                	var c=d.startdate;
                	var d1=d.enddate;
                	var e=d.price;
                
                	if(b =="false")
                		{
                		window.location="account.html?id="+a+"&price="+e;
                		}
                	else
                		{
                		
                		window.location="list_driver.html?id="+a+"&startdate="+c+"&enddate="+d1+"&price="+e;
                		}
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    }
    function submit()
    {
    	 var file=$scope.image;
         carnewService.uploadimage(file);
    }
    function next()
    {
        if(self.car.companyname!=null){
        	carnewService.setCarData(self.car);
        	//carnewService.createCar( carnewService.getCarData());
            
        
        	$state.go('car1new');
           
//            carnewService.createUser(self.car)
//            .then(
//            		 //console.log('create User method', self.user);		
//                     function(errResponse)
//                     {
//                console.error('Error while creating User');
//            }
//        );
        }
        else
        {
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.car.car1id);
        }
    }
    function submit1() {
        if(self.license.licensenumber!=null){
        	
        	
            carnewService.setLicenseData(self.license);
            carnewService.createLicense(
                    carnewService.getLicenseData());
            $state.go('car2new');
        //var carObject = carnewService.getCarData();
        
      /* carObject.licencenumber= self.licence.licencenumber;
       carObject.firstname=self.licenece.firstname;
       carObject.middlename=self.licenece.middlename;
       carObject.lastname=self.licenece.lastname;
       carObject.dateofbirth=self.licenece.dateofbirth;
       carObject.issuingstate=self.licenece.issuingstate;*/
        	//carnewService.setCarData(carObject);
    
        	
        //	self.car=carnewService.getCarData();
        
        	
         //   carnewService.createCar(carnewService.getCarData());
      
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.car.id);
        }
    }
    function submit2() {
        if(self.carbooking.carduration!=null){
        	self.carbooking.temppickupdate=self.carbooking.pickupdate;
        	self.carbooking.tempdropoffdate=self.carbooking.dropoffdate;
            carnewService.setBookingData(self.carbooking);
            
            carnewService.createBooking(
                    carnewService.getBookingData());
            $state.go('car5new');
        //var carObject = carnewService.getCarData();
        
      /* carObject.licencenumber= self.licence.licencenumber;
       carObject.firstname=self.licenece.firstname;
       carObject.middlename=self.licenece.middlename;
       carObject.lastname=self.licenece.lastname;
       carObject.dateofbirth=self.licenece.dateofbirth;
       carObject.issuingstate=self.licenece.issuingstate;*/
        	//carnewService.setCarData(carObject);
    
        	
        //	self.car=carnewService.getCarData();
        
        	
         //   carnewService.createCar(carnewService.getCarData());
      
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.car.id);
        }
    }
    
    function submit3() {
        if(self.car.carplatenumber!=null)
        {
        	
        	
        	var carObject = carnewService.getCarData();
            
            carObject.carplatenumber= self.car.carplatenumber;
            
            
           carnewService.createFeature(self.carfeature);
           
          
           
             carObject.cardescription=self.car.cardescription;
             carObject.price=self.car.price;
             
           
             carnewService.setCarData(carObject);
             $state.go('car3new');
            // carnewService.createCar(carnewService.getCarData());
      
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.car.id);
        }
    }
    function submit4() {
        if(self.car.comanyinfo!=null){
        	
        	
        	var carObject = carnewService.getCarData();
            
            carObject.comanyinfo= self.car.comanyinfo;
             carObject.businessaddress=self.car.businessaddress;
             carObject.website=self.car.website;
             carObject.insuranceprovider=self.car.insuranceprovider;
           carObject.policynumber=self.car.policynumber;
             carnewService.setCarData(carObject);
        // self.car=carnewService.getCarData();
            // angular.extend(self.car,self.carfeature);
             console.log(self.car);
           //  carnewService.createCar(self.car);
             carnewService.createCar(carnewService.getCarData());
             $state.go('car4new');
      
        }else{
          //  updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.car.id);
        }
    }
    
    function updatecarinportal(id)
    {
    	carnewService.updatecarinportal(id)
    	.then(
                function(d) {
                	
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    }
   
   
    
    function getauthcardetails()
    {
    	carnewService.getauthcardetails()
    	.then(
                function(d) {
                	var cardetails={};
                    self.cardetailswithimage = d.cardetailwithimage;
                    $scope.totalItems = self.cardetailswithimage.length;
                  console.log(self.cardetailswithimage.length);
                   //console.log(self.cardetailswithimage);
                   
                   
                   
                   
                  /* cardetailswithimage.forEach(function(element) {
                	   if(element==1){
                		   
                	  
                	    console.log(element.value);
                	   }
                	});*/
                   for(var i=0; i < self.cardetailswithimage.length; i++) {
                       //console.log(self.cardetailswithimage[i].car);
                	
                   //  self.car=self.cardetailswithimage[i].car;
                    
                       //console.log(self.cardetailswithimage[i].b); 
                       //console.log(self.cars.length);
                       
                   /*   var arrayBufferView = new Uint8Array(self.cardetailswithimage[i].b);
                       var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                       var urlCreator = window.URL || window.webkitURL;
                       imageUrl = urlCreator.createObjectURL(blob);
                       console.log(imageUrl);
                       self.cardetailswithimage[i].url=imageUrl;*/
                      
                   }
                   
                   // alert( self.users);
                    //cardetails.car1id=d.car.car1id;
                    //console.log(cardetails.car1id);
                   console.log(self.cars);
                   console.log(self.cardetailswithimage);
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    }
  
    function getcardetails()
    {
    	carnewService.getcardetails()
    	.then(
                function(d) {
                	var cardetails={};
                    self.cardetailswithimage = d.cardetailwithimage;
                    $scope.totalItems = self.cardetailswithimage.length;
                  console.log(self.cardetailswithimage.length);
                   //console.log(self.cardetailswithimage);
                   
                   
                   
                   
                  /* cardetailswithimage.forEach(function(element) {
                	   if(element==1){
                		   
                	  
                	    console.log(element.value);
                	   }
                	});*/
                   for(var i=0; i < self.cardetailswithimage.length; i++) {
                       //console.log(self.cardetailswithimage[i].car);
                	
                   //  self.car=self.cardetailswithimage[i].car;
                    
                       //console.log(self.cardetailswithimage[i].b); 
                       //console.log(self.cars.length);
                       
                   /*   var arrayBufferView = new Uint8Array(self.cardetailswithimage[i].b);
                       var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                       var urlCreator = window.URL || window.webkitURL;
                       imageUrl = urlCreator.createObjectURL(blob);
                       console.log(imageUrl);
                       self.cardetailswithimage[i].url=imageUrl;*/
                      
                   }
                   
                   // alert( self.users);
                    //cardetails.car1id=d.car.car1id;
                    //console.log(cardetails.car1id);
                   console.log(self.cars);
                   console.log(self.cardetailswithimage);
                   

              
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');

               	
                }
              
            );
    }
    
    function getreturncardetails()
    {
    	carnewService.getreturncardetails()
    	.then(
                function(d) {
                	var cars={};
                    self.cardetailswithimage = d.cardetailwithimage;
                    $scope.totalItems = self.cardetailswithimage.length;
                  console.log(self.cardetailswithimage.length);
                   //console.log(self.cardetailswithimage);
                   
                   
                   
                   
                  /* cardetailswithimage.forEach(function(element) {
                	   if(element==1){
                		   
                	  
                	    console.log(element.value);
                	   }
                	});*/
                   for(var i=0; i < self.cardetailswithimage.length; i++) {
                       //console.log(self.cardetailswithimage[i].car);
                	
                   //  self.car=self.cardetailswithimage[i].car;
                    
                       //console.log(self.cardetailswithimage[i].b); 
                       //console.log(self.cars.length);
                       
                   /*   var arrayBufferView = new Uint8Array(self.cardetailswithimage[i].b);
                       var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                       var urlCreator = window.URL || window.webkitURL;
                       imageUrl = urlCreator.createObjectURL(blob);
                       console.log(imageUrl);
                       self.cardetailswithimage[i].url=imageUrl;*/
                      
                   }
                   
                   // alert( self.users);
                    //cardetails.car1id=d.car.car1id;
                    //console.log(cardetails.car1id);
                   console.log(self.cars);
                   console.log(self.cardetailswithimage);
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    }
    
    
    
    
    function golisting(id)
    {
    	//window.location="listing-detail.html";
    	//$location.url('http://localhost:8080/list#/carnew');
    	//$window.location.href ='http://localhost:8080/list#/carnew';
    	
    	/*carnewService.golisting(id)	
    	.then(
                function(d)
                {
                	
                	
                	self.carone=d.cardetailwithimage1;
                	console.log(self.carone.car);
                	//window.location="listing-detail.html";
                	
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );*/ 	
    	
    }
    function historyvehicle()
    {
    	
    	carnewService.historyvehicle()	
    	.then(
                
                	function(d) {
                    	var cardetails={};
                        self.cardetailswithimage = d.cardetailwithimage;
                        $scope.totalItems = self.cardetailswithimage.length;
                      console.log(self.cardetailswithimage.length);
                       //console.log(self.cardetailswithimage);
                       
                       
                       
                       
                      /* cardetailswithimage.forEach(function(element) {
                    	   if(element==1){
                    		   
                    	  
                    	    console.log(element.value);
                    	   }
                    	});*/
                       for(var i=0; i < self.cardetailswithimage.length; i++) {
                           //console.log(self.cardetailswithimage[i].car);
                    	
                       //  self.car=self.cardetailswithimage[i].car;
                        
                           //console.log(self.cardetailswithimage[i].b); 
                           //console.log(self.cars.length);
                           
                       /*   var arrayBufferView = new Uint8Array(self.cardetailswithimage[i].b);
                           var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                           var urlCreator = window.URL || window.webkitURL;
                           imageUrl = urlCreator.createObjectURL(blob);
                           console.log(imageUrl);
                           self.cardetailswithimage[i].url=imageUrl;*/
                          
                       }
                       
                       // alert( self.users);
                        //cardetails.car1id=d.car.car1id;
                        //console.log(cardetails.car1id);
                       console.log(self.cars);
                       console.log(self.cardetailswithimage);
                    
              
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                }
              
            );
    }
    function updateAndNotification(car,id)
    {
    	console.log(car);
    	self.car.isAuthenticated=car.isAuthenticated;
    	self.car.car1id=id;
    	self.car.isActive=car.isActive;
    	/*self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;
    	self.car.=car.;*/
    	
    	console.log(self.car);
    	carnewService.updateAndNotification(self.car,id)	
    	.then(
                function(d)
                {

               	 $scope.successMessage = "car successfully authenticated";
                    $scope.successMessagebool = true;
                    $timeout(function () {
                        $scope.successMessagebool = false;
                       
                    }, 5000);
                	
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');

               	 $scope.successMessage = "car successfully authenticated";
                    $scope.successMessagebool = true;
                    $timeout(function () {
                        $scope.successMessagebool = false;
                       
                    }, 5000);
                }
              
            );
    	
    }
    function getbookingdata()
    {
    	var url=new URL(window.location.href);
    	var c=url.searchParams.get("id");
    	console.log(c);
    	carnewService.getbookingdata(c)
    	.then(
                function(d)
                {
                	console.log(d.cardetailwithimage);
                	
                	self.carbooking=d.cardetailwithimage.carbooking;
                	self.name=d.cardetailwithimage.ownername;
                console.log(self.carbooking);
                	
                								
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                });
    	
    	
    }
function onecar()
    {
		var url=new URL(window.location.href);
		var c=url.searchParams.get("id");
    	console.log(c);
    	carnewService.golisting(c)	
    	.then(
                function(d)
                {
                	
                	
                	self.carone=d.cardetailwithimage1;
                	console.log(self.carone.car);
                	
                	f1=carone.car.carfeature.allwheeldrive;
                	console.log(f1);
                	f2=self.carone.car.carfeature.audioinput;
                	f3=self.carone.car.carfeature.bikerack;
                	f4=self.carone.car.carfeature.bluetooth;
                	f5=self.carone.car.carfeature.childseat;
                	f6=self.carone.car.carfeature.convertible;
                	f7=self.carone.car.carfeature.evhybrid;
                	f8=self.carone.car.carfeature.gps;
                	f9=self.carone.car.carfeature.heatedseats;
                	f10=self.carone.car.carfeature.petfriendly;
                	f11=self.carone.car.carfeature.skirack;
                	f12=self.carone.car.carfeature.snowtires;
                	f13=self.carone.car.carfeature.sunroof;
                	f14=self.carone.car.carfeature.tollpass;
                	f15=self.carone.car.carfeature.usbinput;
                	
                								
                
                },
                function(errResponse){
                    console.error('Error while fetching Cardetails');
                });
    	
    	
    }
function comparecar()
{
	var url=new URL(window.location.href);
	var c=url.searchParams.get("id1");
	console.log(c);
	var c1=url.searchParams.get("id2");
	console.log(c1);
	var c2=url.searchParams.get("id3");
	console.log(c2);
	carnewService.comparecar(c,c1,c2).then(
            function(d)
            {
            	
            	
            	console.log(d.cardetailwithimage);
            	
            	 self.cardetailswithimage=d.cardetailwithimage;
            		console.log(self.cardetailswithimage.length);	
            		// for(var i=0; i < self.cardetailswithimage.length; i++) {
                      //  console.log(self.cardetailswithimage[i].car);
                  	
                      //self.cars=self.cardetailswithimage[i].car;
                      
                         //console.log(self.cardetailswithimage[i].b); 
                         //console.log(self.cars.length);
                         
                     /*   var arrayBufferView = new Uint8Array(self.cardetailswithimage[i].b);
                         var blob = new Blob([arrayBufferView], { type: "image/jpeg" });
                         var urlCreator = window.URL || window.webkitURL;
                         imageUrl = urlCreator.createObjectURL(blob);
                         console.log(imageUrl);
                         self.cardetailswithimage[i].url=imageUrl;*/
                        
                   //  }
            
            },
            function(errResponse){
                console.error('Error while fetching Cardetails');
            });
	
	}
    function search()
    {
    	carnewService.search(self.carbooking);
    }
    function getImageUrlUsingBlob(blob)
    {
    	  var arrayBufferView = new Uint8Array(blob);
          var blob = new Blob([arrayBufferView], { type: "image/jpeg/png" });
          var urlCreator = window.URL || window.webkitURL;
          imageUrl = urlCreator.createObjectURL(blob);
        return imageUrl;
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



