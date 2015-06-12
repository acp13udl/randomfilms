/**
 * Created by Lluís on 12/6/15.
 */
(function(){
    var app = angular.module("favoritesDirective", [ ]);

    app.directive('favoritesDirective', function(){
        console.log("favorites")
        return {
            restrict: 'E',
            templateUrl: 'favorites/favorites.html',

        };
    });

}());