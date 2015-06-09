/**
 * Created by adrian on 8/6/15.
 */

 (function() {
     var app = angular.module("randomFilmsModule", []);

     app.directive('randomFilmsModule', function () {
         return {
             restrict: 'E',
             templateUrl: 'random-film/random-film.html',
             controller: function () {
                 this.tab = 1;

                 this.setTab = function (newValue) {
                     this.tab = newValue;
                 };

                 this.isSet = function (tabName) {
                     return this.tab === tabName;
                 }
             },
             controllerAs: 'film'
         };
     });

 });