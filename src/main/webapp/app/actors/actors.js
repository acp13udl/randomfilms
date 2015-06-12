/**
 * Created by adrian on 12/6/15.
 */

(function(){
    var app = angular.module("actorsDirective", [ ]);

    app.directive('actorsDirective', function(){
        console.log("actorsDirective");
        return {
            restrict: 'E',
            templateUrl: 'actors/actors.html'
        };
    });

}());