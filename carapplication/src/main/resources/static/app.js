
'use strict';

var App = angular.module('myApp',['ui.router','ui.bootstrap','ngSanitize','ngFileUpload']);
App.directive('toggleCheckbox', function() {
	  // based on https://github.com/minhur/bootstrap-toggle/issues/19
	  
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function (scope, element, attributes, ngModelController) {
	      element.on('change.toggle', function(event) { // note that ".toogle" is our namespace, used further down to remove the handler again
	        var checked = element.prop('checked');
	        ngModelController.$setViewValue(checked);
	      });
	                
	      ngModelController.$render = function() {
	        element.bootstrapToggle(ngModelController.$viewValue ? 'on' : 'off');
	      };
      
	      scope.$on('$destroy', function() {
	        // clean up
	        element.off('change.toggle');
	        element.bootstrapToggle('destroy');
	      });
	              
	      // we set the 'checked' property once so the Bootstrap toggle is initialized to the correct value, i.e.,  without flashing the 'off' state and then switch to the 'on' state in case of an initial value of 'true';
	      // this is not needed if your markup already contains the correct 'checked' property;
	      // note that we can't use ngModelController.$viewValue since at this stage, it's still uninitialized as NaN
	      var initialValue = scope.$eval(attributes.ngModel);
	      element.prop('checked', initialValue);
	    }
	  };
	});

App.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/carnew');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('carnew', {
            url: '/carnew',
            
            
            templateUrl: 'carownerdetails1.html',
            controller:'carcontroller as cc'
        })
       
        .state('car1new', {
            url: '/car1new',
			templateUrl: 'ownerlicense.html',
				 controller:'carcontroller as cc'
            
        })
    .state('car2new', {
        url: '/car2new',
      
		templateUrl: 'featurecheck.html',
			 controller:'carcontroller as cc'
        
    })
    .state('car3new', {
        url: '/car3new',
		templateUrl:'policy.html',
			 controller:'carcontroller as cc'
        
    })
        
    
    .state('car4new', {
        url: '/car4new',
		templateUrl: 'pickupdropoff.html',
			 controller:'carcontroller as cc'
        
    })
       .state('car5new', {
        url: '/car5new',
		templateUrl: 'carimageupload.html',
	    controller:'MyCtrl'
        
    });
        
});