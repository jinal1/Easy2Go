app1.controller('notificationcontroller', ['$scope', 'notificationservice', function($scope,notificationservice) {
    var self = this;
    //self.user={userName:'',emailid:'',mobilenumber:'',password:'',confirmpassword:''};
    //self.users=[];

    self.submitnotification = submitnotification;
   
    function submitnotification() {
        if(true){
          //  console.log('Saving New User', self.user);
            notificationservice.createNotification()
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
