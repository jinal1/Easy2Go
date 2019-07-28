'use strict';
angular.module('myApp').controller('loginController', ['$scope', 'loginService', function($scope, loginService) {
    var self = this;
    self.user={username:'',password:''};
    self.users=[];

    self.submit = submit;
   
    function submit() {
        if(self.user.username!=null){
            console.log('Saving New User', self.user);
            loginService.createUser(self.user)
            .then(
            		 //console.log('create User method', self.user);		
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

}]);
