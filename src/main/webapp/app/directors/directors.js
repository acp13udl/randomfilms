/**
 * Created by adrian on 12/6/15.
 */

(function(){
    var app = angular.module("directorsDirective", [ ]);

    app.directive('directorsDirective', function(){
        console.log("directorsDirective");
        return {
            restrict: 'E',
            templateUrl: 'directors/directors.html',
            controller: function() {
            }
        };
    });

}());
