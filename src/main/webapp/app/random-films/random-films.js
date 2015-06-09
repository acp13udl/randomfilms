/**
 * Created by adrian on 8/6/15.
 */

 (function() {
     var app = angular.module('randomFilmsModule', []);

     app.directive('randomFilmsModule', function () {
         return {
             restrict: 'E',
             templateUrl: 'random-films/random-films.html',
             controller: function () {
                 return angular.element($('randomFilmsController')).scope().get().films;
             },
             controllerAs: 'randomfilms'
         };
     });

 });