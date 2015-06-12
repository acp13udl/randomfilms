/**
 * Created by adrian on 12/6/15.
 */
(function(){
    var app = angular.module("authUser", [ ]);

    app.directive("authUser", function(){
        return {
            restrict: "E",
            controller: function($rootScope, $scope, $http) {
                        console.log('controller');
                var authenticate = function(credentials, callback) {

                    $http.get('../users/session').success(function(data) {
                        if (data.username) {
                            $rootScope.authenticated = true;
                        } else {
                            $rootScope.authenticated = false;
                        }
                        callback && callback();
                    }).error(function() {
                        $rootScope.authenticated = false;
                        callback && callback();
                    });

                }

                $scope.credentials = {};
                $scope.login = function() {
                    authenticate($scope.credentials, function() {
                        if ($rootScope.authenticated) {
                            $scope.error = false;
                        } else {
                            $scope.error = true;
                        }
                    });
                };

            },
            controllerAs: 'login'
        };
    });

})();